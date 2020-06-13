package com.example.avvmarket;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.avvmarket.data.DatabaseContract.StocksEntry;

public class BoughtAdapterClass extends CursorAdapter {

    public BoughtAdapterClass(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
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
        int buypriceColumnIndex = cursor.getColumnIndex(StocksEntry.COLUMN_BUYPRICE);
        int nameColumnIndex = cursor.getColumnIndex(StocksEntry.COLUMN_NAME);

        String code = cursor.getString(codeColumnIndex);
        String name = cursor.getString(nameColumnIndex);
        int currentprice = cursor.getInt(currentpriceColumnIndex);
        int buyprice = cursor.getInt(buypriceColumnIndex);
        int change = currentprice - buyprice;
        double pcntchange =(double)((change/buyprice)*100);

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
        TVstartprice.setText("Buy: " + buyprice + ".00");
        String pcntschange = pcntchange + "%";
        TVpcntchange.setText(pcntschange);

    }
}
