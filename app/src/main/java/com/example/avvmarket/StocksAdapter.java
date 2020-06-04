package com.example.avvmarket;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StocksAdapter extends ArrayAdapter<StocksClass> {

    public StocksAdapter(Activity context, ArrayList<StocksClass> stocksClass){
        super(context, 0, stocksClass);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View homeListItemView =convertView;
        if(convertView == null) {
            homeListItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.homelist_item, parent, false);
        }

        StocksClass currentStock =getItem(position);
        TextView name = homeListItemView.findViewById(R.id.TVname);
        name.setText(currentStock.getCode());

        TextView currentprice = homeListItemView.findViewById(R.id.TVcurrentprice);
        currentprice.setText(Integer.toString(currentStock.getCurrentprice()));

        TextView change = homeListItemView.findViewById(R.id.TVchange);
        int chng = currentStock.getChange();
        if(chng < 0){
            change.setTextColor(Color.RED);
        }
        else {
            change.setTextColor(Color.GREEN);
        }
        change.setText(Integer.toString(chng));

        return homeListItemView;
    }
}
