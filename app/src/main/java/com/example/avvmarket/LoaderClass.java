package com.example.avvmarket;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import com.example.avvmarket.ui.home.HomeFragment;

import java.util.ArrayList;

public class LoaderClass extends AsyncTaskLoader<ArrayList<StocksClass>> {

    private static final String LOG_TAG = LoaderClass.class.getSimpleName();
    ArrayList<StocksClass>stocks;

    public LoaderClass(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG, "onStartLoading");
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<StocksClass> loadInBackground() {
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "Thread error");
            e.printStackTrace();
        }
        return null;
        //return Stocks.changeAllStocks();
    }
}
