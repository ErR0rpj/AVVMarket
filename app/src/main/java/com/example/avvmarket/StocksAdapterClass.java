package com.example.avvmarket;

import android.app.Activity;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.avvmarket.data.DatabaseContract.StocksEntry;
import com.example.avvmarket.data.StocksFirebaseColumns;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StocksAdapterClass extends ArrayAdapter<StocksFirebaseColumns> {

    private final String LOG_TAG =StocksAdapterClass.class.getSimpleName();
    private static DecimalFormat df = new DecimalFormat("0.00");

    public StocksAdapterClass(Activity context, ArrayList<StocksFirebaseColumns> list){
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listItemView= convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.homelist_item, parent, false);
        }

        TextView TVcode = listItemView.findViewById(R.id.TVcode);
        TextView TVcurrentprice = listItemView.findViewById(R.id.TVcurrentprice);
        TextView TVchange = listItemView.findViewById(R.id.TVchange);
        TextView TVpcntchange = listItemView.findViewById(R.id.TVpcntchange);
        TextView TVstartprice = listItemView.findViewById(R.id.TVstartprice);
        TextView TVname = listItemView.findViewById(R.id.TVname);
        TextView TVcolor = listItemView.findViewById(R.id.TVcolor);

        StocksFirebaseColumns currentListItem = getItem(position);

        if(currentListItem == null){
            Log.e(LOG_TAG, "currentListItem is null");
            return listItemView;
        }

        String code = currentListItem.getCode();
        int currentprice = currentListItem.getCurrentprice();
        String gang = currentListItem.getGang();
        String name = currentListItem.getName();
        int startprice = currentListItem.getStartprice();
        int change = currentprice - startprice;
        double pcntchange =(((double)change/(double)startprice)*100);

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

        TVcode.setText(code);
        TVcurrentprice.setText(currentprice + ".00");
        TVchange.setText(change + ".00");
        TVname.setText(name);
        TVstartprice.setText("Open: " + startprice + ".00");
        String pcntschange = df.format(pcntchange) + "%";
        TVpcntchange.setText(pcntschange);

        return listItemView;

    }

    /*@Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.homelist_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView TVcode = view.findViewById(R.id.TVcode);
        TextView TVcurrentprice = view.findViewById(R.id.TVcurrentprice);
        TextView TVchange = view.findViewById(R.id.TVchange);
        TextView TVpcntchange = view.findViewById(R.id.TVpcntchange);
        TextView TVstartprice = view.findViewById(R.id.TVstartprice);
        TextView TVname = view.findViewById(R.id.TVname);
        TextView TVcolor = view.findViewById(R.id.TVcolor);

        int codeColumnIndex = cursor.getColumnIndex(StocksEntry.COLUMN_CODE);
        int currentpriceColumnIndex = cursor.getColumnIndex(StocksEntry.COLUMN_CURRENTPRICE);
        int startpriceColumnIndex = cursor.getColumnIndex(StocksEntry.COLUMN_STARTPRICE);
        int nameColumnIndex = cursor.getColumnIndex(StocksEntry.COLUMN_NAME);

        String code = cursor.getString(codeColumnIndex);
        String name = cursor.getString(nameColumnIndex);
        int currentprice = cursor.getInt(currentpriceColumnIndex);
        int startprice = cursor.getInt(startpriceColumnIndex);
        int change = currentprice - startprice;
        double pcntchange =(double)((change/startprice)*100);

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

        TVcode.setText(code);
        TVcurrentprice.setText(currentprice + ".00");
        TVchange.setText(change + ".00");
        TVname.setText(name);
        TVstartprice.setText("Open: " + startprice + ".00");
        String pcntschange = pcntchange + "%";
        TVpcntchange.setText(pcntschange);
    }*/
}
