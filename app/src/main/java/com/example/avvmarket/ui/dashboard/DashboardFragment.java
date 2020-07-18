package com.example.avvmarket.ui.dashboard;

import android.content.ContentValues;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.avvmarket.BoughtAdapterClass;
import com.example.avvmarket.R;
import com.example.avvmarket.StocksOwnDetails;
import com.example.avvmarket.data.DBHelper;
import com.example.avvmarket.data.DatabaseContract.StocksEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

import static com.example.avvmarket.MainActivity.funds;
import static com.example.avvmarket.MainActivity.uid;

public class DashboardFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_CONSTANT = 0;
    private BoughtAdapterClass adapter;
    private static final String LOG_TAG = DashboardFragment.class.getSimpleName();
    private Button BTNbuy;
    private Button BTNsell;
    private EditText ETtofind;
    private DBHelper mdbHelper;
    private TextView textView;
    //private TextView TVwhenempty;
    private ListView listportfolio;

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    public static DatabaseReference m2databaseReference;

    private static DecimalFormat df = new DecimalFormat("0.00");
    private static int boughtprice ;
    private static int curprice;
    private static String selection;
    private static String[] selectionArgs = {""};
    private static String search;
    private Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_dashboard,container,false);
        //displayDatabaseInfo(view);

        textView = view.findViewById(R.id.textView);
        BTNbuy = view.findViewById(R.id.BTNbuy);
        BTNsell = view.findViewById(R.id.BTNsell);
        ETtofind = view.findViewById(R.id.ETtofind);
        listportfolio = view.findViewById(R.id.LISTportfolio);

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

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETtofind.setText(textView.getText());
            }
        });

        BTNbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                Log.e(LOG_TAG, "isConnected = " + isConnected);

                if (!isConnected) {
                    Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }

                else {

                    search = ETtofind.getText().toString().trim();
                    search = search.toUpperCase();
                    SQLiteDatabase db = mdbHelper.getReadableDatabase();
                    String[] projection = {StocksEntry.COLUMN_CODE, StocksEntry.COLUMN_NAME,
                            StocksEntry.COLUMN_CURRENTPRICE, StocksEntry.COLUMN_ISBUY};

                    cursor = db.rawQuery("SELECT " + StocksEntry.COLUMN_CODE + "," +
                            StocksEntry.COLUMN_NAME + "," + StocksEntry.COLUMN_CURRENTPRICE + ","
                            + StocksEntry.COLUMN_ISBUY + " FROM "
                            + StocksEntry.TABLE_NAME + " WHERE " + StocksEntry.COLUMN_CODE
                            + "='" + search + "';", null);
                    //Log.e(LOG_TAG,"Column Count = "+ cursor.getColumnCount());

                    if (cursor.getCount() != 1) {
                        Toast.makeText(getActivity(), "Please, enter a valid code!", Toast.LENGTH_SHORT).show();
                    } else if (cursor.moveToNext()) {
                        if (cursor.getInt(3) == 1) {
                            Toast.makeText(getActivity(), "This Stock is already Owned by you!", Toast.LENGTH_LONG).show();
                        } else {

                            mFirebaseDatabase = FirebaseDatabase.getInstance();
                            mDatabaseReference = mFirebaseDatabase.getReference();

                            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    boughtprice = dataSnapshot.child("stocks").child(search).child("currentprice").getValue(Integer.class);
                                    funds = dataSnapshot.child("users").child(uid).child("funds").getValue(Integer.class);
                                    Log.e(LOG_TAG, "In BTNbuy currentprice retrieved " + boughtprice);
                                    Log.e(LOG_TAG, "In BTNbuy funds retrieved " + funds);

                                    if (funds >= boughtprice && boughtprice > 0) {
                                        funds = funds - boughtprice;
                                        m2databaseReference = mFirebaseDatabase.getReference().child("users").child(uid);
                                        m2databaseReference.child("funds").setValue(funds);

                                        m2databaseReference = mFirebaseDatabase.getReference().child("stocks");
                                        m2databaseReference.child(search).child("buycount").setValue(boughtprice);

                                        StocksOwnDetails sod = new StocksOwnDetails(search, boughtprice);
                                        mDatabaseReference = mFirebaseDatabase.getReference().child("users").child(uid).child("stocksown").child(search);
                                        mDatabaseReference.setValue(sod);

                                        String selection = StocksEntry.COLUMN_CODE + "=?";
                                        String[] selectionargs = {search};

                                        ContentValues values = new ContentValues();
                                        values.put(StocksEntry.COLUMN_ISBUY, 1);
                                        values.put(StocksEntry.COLUMN_BUYPRICE, boughtprice);
                                        values.put(StocksEntry.COLUMN_CURRENTPRICE, boughtprice);

                                        int updatedrows;
                                        updatedrows = getActivity().getContentResolver().update(StocksEntry.CONTENT_URI,
                                                values, selection, selectionargs);
                                        Log.e(LOG_TAG, "Rows updated:" + updatedrows);

                                        Toast.makeText(getActivity(), cursor.getString(1) + " successfully bought at Rs. " + boughtprice, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getActivity(), "You do not have enough funds to buy this stock", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Log.e(LOG_TAG, "cancelled currentprice retrieving");
                                }
                            });

                        }
                    }
                    ETtofind.getText().clear();
                }
            }
        });

        BTNsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                Log.e(LOG_TAG, "isConnected = " + isConnected);

                if (!isConnected) {
                    Toast.makeText(getActivity(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
                }

                else {

                    search = ETtofind.getText().toString().trim();
                    search = search.toUpperCase();
                    final Cursor cursor;
                    String[] projection = {StocksEntry.COLUMN_CODE, StocksEntry.COLUMN_NAME,
                            StocksEntry.COLUMN_CURRENTPRICE, StocksEntry.COLUMN_ISBUY};
                    selection = StocksEntry.COLUMN_CODE + "=?";
                    selectionArgs[0] = search;

                    cursor = getActivity().getContentResolver().query(StocksEntry.CONTENT_URI,
                            projection, selection, selectionArgs, null);
                    //Log.e(LOG_TAG, "Column Count = " + cursor.getColumnCount());

                    if (cursor.getCount() != 1) {
                        Toast.makeText(getActivity(), "Please, Enter a valid code", Toast.LENGTH_SHORT).show();
                    } else if (cursor.moveToNext()) {
                        if (cursor.getInt(3) == 0) {
                            Toast.makeText(getActivity(), "You do not own this stock!", Toast.LENGTH_LONG).show();
                        } else {

                            mFirebaseDatabase = FirebaseDatabase.getInstance();
                            mDatabaseReference = mFirebaseDatabase.getReference();

                            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    curprice = dataSnapshot.child("stocks").child(search).child("currentprice").getValue(Integer.class);
                                    funds = dataSnapshot.child("users").child(uid).child("funds").getValue(Integer.class);
                                    Log.e(LOG_TAG, "In BTNsell currentprice retrieved " + curprice);
                                    Log.e(LOG_TAG, "In BTNsell funds retrieved " + funds);

                                    funds += curprice;
                                    m2databaseReference = mFirebaseDatabase.getReference().child("users").child(uid);
                                    m2databaseReference.child("funds").setValue(funds);

                                    m2databaseReference.child("stocksown").child(search).setValue(null);

                                    m2databaseReference = mFirebaseDatabase.getReference().child("stocks");
                                    m2databaseReference.child(search).child("sellcount").setValue(curprice);

                                    String selection2 = StocksEntry.COLUMN_CODE + "=?";

                                    ContentValues values = new ContentValues();
                                    values.put(StocksEntry.COLUMN_ISBUY, 0);

                                    int updatedrows;
                                    updatedrows = getActivity().getContentResolver().update(StocksEntry.CONTENT_URI,
                                            values, selection2, selectionArgs);
                                    Log.e(LOG_TAG, "Rows updated:" + updatedrows);

                                    Toast.makeText(getActivity(), cursor.getString(1) + " Sold successfully at Rs. " + curprice, Toast.LENGTH_LONG).show();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });

                        }
                    }
                    ETtofind.getText().clear();
                }
            }
        });

        adapter = new BoughtAdapterClass(getActivity(), null);
        listportfolio.setAdapter(adapter);

        LoaderManager.getInstance(this).initLoader(LOADER_CONSTANT, null, this).forceLoad();
        Log.e(LOG_TAG, "After init loader");

        return view;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        String[] projection = {StocksEntry._ID, StocksEntry.COLUMN_CODE,
                StocksEntry.COLUMN_NAME, StocksEntry.COLUMN_CURRENTPRICE,
                StocksEntry.COLUMN_BUYPRICE, StocksEntry.COLUMN_ISBUY};
        String selection = StocksEntry.COLUMN_ISBUY + "=1";
        String sortOrder = StocksEntry.COLUMN_CODE + " ASC";

        return new CursorLoader(getActivity(), StocksEntry.CONTENT_URI, projection, selection,
                null, sortOrder);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}