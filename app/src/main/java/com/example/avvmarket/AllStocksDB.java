package com.example.avvmarket;

import android.content.ContentValues;
import android.util.Log;

import com.example.avvmarket.data.DatabaseContract;

import java.util.ArrayList;

public class AllStocksDB {

    private static final String LOG_TAG = AllStocksDB.class.getSimpleName();

    public static ArrayList<ContentValues> beginAllStocks(){
        ArrayList<ContentValues> list = new ArrayList<>();
        ContentValues values = new ContentValues();

        Log.e(LOG_TAG, "beginAllStocks starting.");

        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Vinay Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "VIN_ad");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 6000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 6000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_ADMIN);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Mehul Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "MHUL_HP3");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 750);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 750);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HP3);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Bharat Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "BRT_HP3");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 500);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 500);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HP3);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Yunus Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "YNS_HP3");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 500);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 500);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HP3);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Anas Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "ANAS_x");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 600);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 600);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_EXTRAS);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "BAGHA");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "BAGA_x");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 700);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 700);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_EXTRAS);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Sandeep Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "SNDP_ad");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 3000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 3000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_ADMIN);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Neema mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "NIMA_t");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 1200);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 1200);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_TEACHERS);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Monica Mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "MNK_t");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 1000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 1000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_TEACHERS);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Reshu Mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "RSH_t");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 1350);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 1350);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_TEACHERS);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Director Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "DR_hd");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 12000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 12000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HEAD);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Vikrant Musale Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "VP_hd");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 8500);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 8500);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HEAD);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Chavi Ravi Mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "PM_hd");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 10000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 10000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HEAD);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Nilesh Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "NLS_ad");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 4000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 4000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_ADMIN);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Bilal Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "BIL_ad");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 4500);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 4500);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_ADMIN);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Sujata Mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "SJTA_hd");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 5150);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 5150);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HEAD);
        list.add(values);

        Log.e("StocksClass",String.valueOf(list.size()));

        return list;
    }
}
