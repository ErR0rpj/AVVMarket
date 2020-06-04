package com.example.avvmarket.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.avvmarket.MainActivity;
import com.example.avvmarket.R;
import com.example.avvmarket.StocksAdapter;
import com.example.avvmarket.StocksClass;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<StocksClass>> {

    private ListView homeListView;
    private StocksAdapter adapter;
    private static final String LOG_TAG = HomeFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_home,container,false);
        homeListView = view.findViewById(R.id.LISThome);

        adapter = new StocksAdapter(getActivity(), MainActivity.arlist);
        homeListView.setAdapter(adapter);

        LoaderManager.getInstance(this).initLoader(1, null, this).forceLoad();
        Log.e(LOG_TAG, "After initLoader()");

        return view;
    }

    private void updateUI(ArrayList<StocksClass> stocks){
        if(stocks==null || stocks.size()==0){
            Log.e(LOG_TAG, "UpdateUI: stocks list is empty");
            return;
        }
        adapter.clear();
        adapter.addAll(MainActivity.arlist);
    }

    @NonNull
    @Override
    public Loader<ArrayList<StocksClass>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.e(LOG_TAG, "onCreateLoader()");
        return new com.example.avvmarket.LoaderClass(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<StocksClass>> loader, ArrayList<StocksClass> data) {
        Log.e(LOG_TAG, "onLoadFinished()");
        if(data==null || data.size()==0){
            Log.e(LOG_TAG,"data is null");
        }
        updateUI(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<StocksClass>> loader) {
        Log.e(LOG_TAG, "onLoaderReset");
        updateUI(null);
    }
}