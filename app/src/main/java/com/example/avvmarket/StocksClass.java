package com.example.avvmarket;

public class StocksClass {

    private String name, code, subgroup;
    private int currentprice, change,id, startprice;

    public StocksClass(int id, String name, String code, int change, String subgroup, int currentprice) {
        this.code = code;
        this.name = name;
        this.change = change;
        this.subgroup = subgroup;
        this.currentprice = currentprice;
        this.id = id;
        this.startprice = currentprice;
    }

    public void setCurrentprice(int currentprice) {
        this.currentprice = currentprice;
    }

    public String getName(){
        return name;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getChange() {
        change = currentprice - startprice;
        return change;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public int getCurrentprice() {
        return currentprice;
    }

}
