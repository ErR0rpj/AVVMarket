package com.example.avvmarket.data;

public class StocksFirebaseColumns {

    private String code;
    private int currentprice;
    private  int id;
    private String gang;
    private  String name;
    private int startprice;

    public StocksFirebaseColumns(){}

    public StocksFirebaseColumns(String code, int currentprice, String gang, int id, String name, int startprice) {
        this.code = code;
        this.currentprice = currentprice;
        this.gang = gang;
        this.id = id;
        this.name = name;
        this.startprice = startprice;
    }

    public String getCode() {
        return code;
    }

    public int getCurrentprice() {
        return currentprice;
    }

    public int getId() {
        return id;
    }

    public String getGang() {
        return gang;
    }

    public String getName() {
        return name;
    }

    public int getStartprice() {
        return startprice;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCurrentprice(int currentprice) {
        this.currentprice = currentprice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGang(String gang) {
        this.gang = gang;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartprice(int startprice) {
        this.startprice = startprice;
    }
}
