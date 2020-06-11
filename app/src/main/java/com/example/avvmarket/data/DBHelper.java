package com.example.avvmarket.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.avvmarket.AllStocksDB;
import com.example.avvmarket.data.DatabaseContract.StocksEntry;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = DBHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "avvMarketDB";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(LOG_TAG, "DBHelper onCreate initiating.");
        String SQL_CREATE_STOCKS_TABLE = "CREATE TABLE " +StocksEntry.TABLE_NAME +" ("
                +StocksEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +StocksEntry.COLUMN_NAME +" TEXT NOT NULL, " +StocksEntry.COLUMN_CODE
                +" TEXT NOT NULL, " +StocksEntry.COLUMN_STARTPRICE +" INTEGER NOT NULL, "
                +StocksEntry.COLUMN_CURRENTPRICE +" INTEGER, " +StocksEntry.COLUMN_GANG
                +" INTEGER NOT NULL, " +StocksEntry.COLUMN_BUYPRICE +" INTEGER, "
                +StocksEntry.COLUMN_ISBUY +" INTEGER NOT NULL DEFAULT 0);";
        Log.e(LOG_TAG, SQL_CREATE_STOCKS_TABLE);

        db.execSQL(SQL_CREATE_STOCKS_TABLE);
        insertStocks(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void insertStocks(SQLiteDatabase db){
        ArrayList<ContentValues> list = AllStocksDB.beginAllStocks();
        Log.e("DBHelper:", String.valueOf(list.size()));

        for(int i=0; i<list.size(); i++){
            Log.e("DBHelper:", String.valueOf(list.size()));
            ContentValues values = list.get(i);
            db.insert(StocksEntry.TABLE_NAME, null, values);
        }
    }
}