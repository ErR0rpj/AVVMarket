package com.example.avvmarket;

public class StocksOwnDetails {

    private String code;
    private int boughtprice;

    public StocksOwnDetails() {}

    public StocksOwnDetails(String code, int boughtprice) {
        this.code = code;
        this.boughtprice = boughtprice;
    }

    public String getCode() {
        return code;
    }

    public int getBoughtprice() {
        return boughtprice;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBoughtprice(int boughtprice) {
        this.boughtprice = boughtprice;
    }

}
