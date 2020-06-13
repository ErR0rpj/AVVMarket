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

        values.put(DatabaseContract.StocksEntry._ID, 1);
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Vinay Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "VIN_AD");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 6000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 6000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_ADMIN);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Mehul Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "MHUL_HP3");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 750);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 750);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HP3);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Bharat Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "BRT_HP3");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 500);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 500);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HP3);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Yunus Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "YNS_HP3");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 500);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 500);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HP3);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "हिन्दी सिनेमा");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "ANAS_X");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 600);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 600);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_EXTRAS);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "BAGHA");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "BAGA_X");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 700);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 700);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_EXTRAS);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Sandeep Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "SNDP_AD");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 3000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 3000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_ADMIN);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Neema mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "NIMA_T");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 1200);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 1200);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_TEACHERS);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Monica Mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "MNK_T");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 1000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 1000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_TEACHERS);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Reshu Mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "RSH_T");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 1350);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 1350);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_TEACHERS);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Director Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "DR_HD");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 12000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 12000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HEAD);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Vikrant Musale Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "VP_HD");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 8500);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 8500);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HEAD);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Chavi Ravi Mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "PM_HD");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 10000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 10000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HEAD);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Nilesh Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "NLS_AD");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 4000);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 4000);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_ADMIN);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Bilal Sir");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "BIL_AD");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 4500);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 4500);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_ADMIN);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        values = new ContentValues();
        values.put(DatabaseContract.StocksEntry.COLUMN_NAME, "Sujata Mam");
        values.put(DatabaseContract.StocksEntry.COLUMN_CODE, "SJTA_HD");
        values.put(DatabaseContract.StocksEntry.COLUMN_STARTPRICE, 5150);
        values.put(DatabaseContract.StocksEntry.COLUMN_CURRENTPRICE, 5150);
        values.put(DatabaseContract.StocksEntry.COLUMN_GANG, DatabaseContract.StocksEntry.GANG_HEAD);
        values.put(DatabaseContract.StocksEntry.COLUMN_ISBUY, 0);
        list.add(values);

        Log.e("AllStocksDB: ",String.valueOf(list.size()));

        return list;
    }
}
