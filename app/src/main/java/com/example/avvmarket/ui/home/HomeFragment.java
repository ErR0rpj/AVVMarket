package com.example.avvmarket.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.avvmarket.BoughtAdapterClass;
import com.example.avvmarket.R;
import com.example.avvmarket.StocksAdapterClass;
import com.example.avvmarket.data.DBHelper;
import com.example.avvmarket.data.DatabaseContract.StocksEntry;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private ListView homeListView;
    private static final String LOG_TAG = HomeFragment.class.getSimpleName();
    private StocksAdapterClass adapter;
    private static int LOADER_CONSTANT = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_home,container,false);
        homeListView = view.findViewById(R.id.LISThome);

        displayDatabaseInfo(view);

        adapter = new StocksAdapterClass(getActivity(), null);
        homeListView.setAdapter(adapter);

        LoaderManager.getInstance(this).initLoader(LOADER_CONSTANT, null, this).forceLoad();
        Log.e(LOG_TAG, "After init loader");

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
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}