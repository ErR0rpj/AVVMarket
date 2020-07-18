package com.example.avvmarket.ui.home;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.avvmarket.R;
import com.example.avvmarket.StocksAdapterClass;
import com.example.avvmarket.data.DBHelper;
import com.example.avvmarket.data.DatabaseContract.StocksEntry;
import com.example.avvmarket.data.StocksFirebaseColumns;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView homeListView;
    private static final String LOG_TAG = HomeFragment.class.getSimpleName();
    private StocksAdapterClass adapter;
    private static int LOADER_CONSTANT = 1;

    private static  DecimalFormat df = new DecimalFormat("0.00");

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    public static ChildEventListener mChildEventListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_home,container,false);
        homeListView = view.findViewById(R.id.LISThome);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("stocks");

        ArrayList<StocksFirebaseColumns> list = new ArrayList<>();
        adapter = new StocksAdapterClass(getActivity(), list);
        homeListView.setAdapter(adapter);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                StocksFirebaseColumns sfc = dataSnapshot.getValue(StocksFirebaseColumns.class);
                adapter.add(sfc);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                StocksFirebaseColumns sfc = dataSnapshot.getValue(StocksFirebaseColumns.class);

                int index = sfc.getId();
                int currentprice = sfc.getCurrentprice();
                int startprice = sfc.getStartprice();
                int change = currentprice - startprice;
                double pcntchange =(((double)change/(double)startprice)*100);


                View v = homeListView.getChildAt(index - homeListView.getFirstVisiblePosition());

                if(v == null){
                    Log.e(LOG_TAG, "view in change is null");
                    return;
                }

                TextView TVcurrentprice = v.findViewById(R.id.TVcurrentprice);
                TextView TVchange = v.findViewById(R.id.TVchange);
                TextView TVpcntchange = v.findViewById(R.id.TVpcntchange);
                TextView TVcolor = v.findViewById(R.id.TVcolor);

                if(change<0){
                    TVchange.setTextColor(Color.RED);
                    TVpcntchange.setTextColor(Color.RED);
                    TVcolor.setBackgroundColor(0xFFFF0000);
                }
                else{
                    TVchange.setTextColor(Color.GREEN);
                    TVpcntchange.setTextColor(Color.GREEN);
                    TVcolor.setBackgroundColor(0xFF00FF00);
                }

                String pcntschange = df.format(pcntchange) + "%";
                Log.e(LOG_TAG, pcntchange + "");
                TVpcntchange.setText(pcntschange);
                TVcurrentprice.setText(currentprice + ".00");
                TVchange.setText(change + ".00");

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(LOG_TAG, "Problem retreiving data from database: " + databaseError.getCode());
            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);

        //displayDatabaseInfo(view);

        /*adapter = new StocksAdapterClass(getActivity(), null);
        homeListView.setAdapter(adapter);

        LoaderManager.getInstance(this).initLoader(LOADER_CONSTANT, null, this).forceLoad();
        Log.e(LOG_TAG, "After init loader");*/

        return view;
    }

    private void displayDatabaseInfo(View view){

        DBHelper dbHelper = new DBHelper(getActivity());

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        /*String[] projection = {StocksEntry._ID, StocksEntry.COLUMN_NAME,
        StocksEntry.COLUMN_CODE,
        StocksEntry.COLUMN_STARTPRICE,
        StocksEntry.COLUMN_CURRENTPRICE};
        String sortOrder = StocksEntry.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(
                StocksEntry.TABLE_NAME,
                projection,
                null,null,null,null,sortOrder);

        StocksAdapterClass adapter = new StocksAdapterClass(getActivity(), cursor);
        homeListView.setAdapter(adapter);*/

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        String[] projection = {StocksEntry._ID, StocksEntry.COLUMN_NAME,
                StocksEntry.COLUMN_CODE,
                StocksEntry.COLUMN_STARTPRICE,
                StocksEntry.COLUMN_CURRENTPRICE};
        String sortOrder = StocksEntry.COLUMN_NAME + " ASC";
        return new CursorLoader(getActivity(), StocksEntry.CONTENT_URI, projection,
                null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        //adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        //adapter.swapCursor(null);
    }
}