package com.example.avvmarket;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Stocks {

    public static ArrayList<StocksClass> arlist;

    public ArrayList<StocksClass> getAllStocks(){
        StocksClass vin = new StocksClass(1,"Vinay Sir", "VIN_ad", 100, "Admin", 1000);
        StocksClass bin = new StocksClass(2,"Bindu Mam", "BND_t", 30, "Teacher", 650);
        StocksClass baga = new StocksClass(3,"Bagha","BAGA",-22,"Others",488);
        arlist= new ArrayList<>();
        Collections.addAll(arlist,vin,bin,baga);
        return arlist;
    }

    public static ArrayList<StocksClass> changeAllStocks(){

        Log.e("changeAllStocks", "Updating values");
        Random rand = new Random();

        for(int i=0;i<MainActivity.arlist.size();i++){
            StocksClass tochange = MainActivity.arlist.get(i);
            int pcntchng = rand.nextInt(5);
            int p_n = rand.nextInt(2);

            if(p_n==0){
                pcntchng = pcntchng * (-1);
            }
            pcntchng = 100-pcntchng;
            int currentprice = ((tochange.getCurrentprice())*pcntchng)/100;
            tochange.setCurrentprice(currentprice);
            MainActivity.arlist.set(i,tochange);
            Log.e("Changing to"+MainActivity.arlist.get(i).getName(),Integer.toString(tochange.getCurrentprice()));
        }
        return null;
    }

}
