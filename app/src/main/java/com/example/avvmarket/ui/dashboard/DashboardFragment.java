package com.example.avvmarket.ui.dashboard;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.avvmarket.R;
import com.example.avvmarket.Stocks;
import com.example.avvmarket.data.DBHelper;
import com.example.avvmarket.data.DatabaseContract.StocksEntry;

public class DashboardFragment extends Fragment {

    private static final String LOG_TAG = DashboardFragment.class.getSimpleName();
    private Button BTNbuy;
    private Button BTNsell;
    private EditText ETtofind;
    private DBHelper mdbHelper;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_dashboard,container,false);
        //isplayDatabaseInfo(view);

        textView = view.findViewById(R.id.textView);
        BTNbuy = view.findViewById(R.id.BTNbuy);
        BTNsell = view.findViewById(R.id.BTNsell);
        ETtofind = view.findViewById(R.id.ETtofind);

        mdbHelper = new DBHelper(getActivity());

        ETtofind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Cursor cursor;
                Cursor cursor1;
                SQLiteDatabase db = mdbHelper.getReadableDatabase();

                cursor = db.rawQuery("SELECT " + StocksEntry.COLUMN_CODE+",name " + " FROM "
                        + StocksEntry.TABLE_NAME + " WHERE " + StocksEntry.COLUMN_NAME +
                        " LIKE '" + s +"%';", null);
                cursor1 = db.rawQuery("SELECT " + StocksEntry.COLUMN_CODE+",name " + " FROM "
                        + StocksEntry.TABLE_NAME + " WHERE " + StocksEntry.COLUMN_CODE +
                        " LIKE '" + s +"%';", null);

                if(s.toString().equals("")){
                    textView.setText("");
                }
                else if(cursor.moveToNext()) {
                    textView.setText(cursor.getString(0));
                }
                else if(cursor1.moveToNext()){
                    textView.setText(cursor1.getString(0));
                }
                else{
                    textView.setText("Enter Valid Code");
                }
                cursor.close();
                cursor1.close();
            }
        });

        BTNbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = ETtofind.getText().toString().trim();
                search = search.toUpperCase();
                Cursor cursor;
                SQLiteDatabase db = mdbHelper.getReadableDatabase();
                String[] projection = {StocksEntry.COLUMN_CODE, StocksEntry.COLUMN_NAME,
                StocksEntry.COLUMN_CURRENTPRICE,StocksEntry.COLUMN_ISBUY};

                cursor = db.rawQuery("SELECT " + StocksEntry.COLUMN_CODE + "," +
                        StocksEntry.COLUMN_NAME + "," + StocksEntry.COLUMN_CURRENTPRICE + ","
                        + StocksEntry.COLUMN_ISBUY + " FROM "
                        + StocksEntry.TABLE_NAME + " WHERE " + StocksEntry.COLUMN_CODE
                        + "='" + search + "';",null);
                //Log.e(LOG_TAG,"Column Count = "+ cursor.getColumnCount());

                if(cursor.getCount()!=1){
                    Toast.makeText(getActivity(),"Please, enter a valid code!", Toast.LENGTH_SHORT).show();
                }
                else if(cursor.moveToNext()) {
                    if (cursor.getInt(3) == 1) {
                        Toast.makeText(getActivity(), "This Stock is already Owned by you!", Toast.LENGTH_LONG).show();
                    }
                    else if (cursor.getInt(2) > StocksEntry.FUND) {
                        Toast.makeText(getActivity(), "You do not have enough funds in your account", Toast.LENGTH_LONG).show();
                    }
                    else {
                        int curprice = cursor.getInt(2);
                        StocksEntry.FUND = StocksEntry.FUND - curprice;

                        String selection = StocksEntry.COLUMN_CODE + "=?";
                        String[] selectionargs = {search};

                        ContentValues values = new ContentValues();
                        values.put(StocksEntry.COLUMN_ISBUY, 1);
                        values.put(StocksEntry.COLUMN_BUYPRICE, curprice);

                        int updatedrows;
                        updatedrows = getActivity().getContentResolver().update(StocksEntry.CONTENT_URI,
                                values, selection, selectionargs);
                        Log.e(LOG_TAG, "Rows updated:" + updatedrows);

                        Toast.makeText(getActivity(), cursor.getString(1) + " successfully bought!", Toast.LENGTH_LONG).show();
                    }
                }

                cursor.close();
            }
        });

        BTNsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = ETtofind.getText().toString().trim();
                search = search.toUpperCase();
                Cursor cursor;
                String[] projection = {StocksEntry.COLUMN_CODE, StocksEntry.COLUMN_NAME,
                StocksEntry.COLUMN_CURRENTPRICE, StocksEntry.COLUMN_ISBUY};
                String selection = StocksEntry.COLUMN_CODE + "=?";
                String[] selectionArgs = {search};

                cursor = getActivity().getContentResolver().query(StocksEntry.CONTENT_URI,
                        projection, selection, selectionArgs, null);
                //Log.e(LOG_TAG, "Column Count = " + cursor.getColumnCount());

                if(cursor.getCount()!=1){
                    Toast.makeText(getActivity(), "Please, Enter a valid code", Toast.LENGTH_SHORT).show();
                }
                else if(cursor.moveToNext()){
                    if(cursor.getInt(3) == 0){
                        Toast.makeText(getActivity(), "You do not own this stock!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        int curprice = cursor.getInt(2);
                        StocksEntry.FUND = StocksEntry.FUND + curprice;

                        String selection2 = StocksEntry.COLUMN_CODE + "=?";

                        ContentValues values = new ContentValues();
                        values.put(StocksEntry.COLUMN_ISBUY, 0);

                        int updatedrows;
                        updatedrows = getActivity().getContentResolver().update(StocksEntry.CONTENT_URI,
                                values, selection2, selectionArgs);
                        Log.e(LOG_TAG, "Rows updated:" + updatedrows);

                        Toast.makeText(getActivity(),cursor.getString(1) + " Sold successfully!",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        return view;
    }

    private void displayDatabaseInfo(View view){

        String[] projection = {
                StocksEntry._ID,
                StocksEntry.COLUMN_NAME,
                StocksEntry.COLUMN_STARTPRICE,
                StocksEntry.COLUMN_ISBUY
        };
        String sortOrder = StocksEntry.COLUMN_NAME + " ASC";
        String selection = StocksEntry.COLUMN_ISBUY + "==1";

        Cursor cursor = getActivity().getContentResolver().query(StocksEntry.CONTENT_URI, projection,
                selection, null, sortOrder);

        try{
            textView.setText(String.valueOf(cursor.getCount()));
            while(cursor.moveToNext()){
                textView.append(cursor.getString(0)+"\n");
            }

        }catch(Exception e){
            Log.e(LOG_TAG, "displayDatabaseInfo: "+e.getMessage());
        }
        finally {
            cursor.close();
        }
    }

}