package com.gmail.vuyotm.fixme.market;

public class Stock {

    private final String    tickerSymbol;
    private float           price;
    private int             qty;

    public Stock(String tickerSymbol, float price, int qty) {
        this.tickerSymbol = tickerSymbol;
        this.price = price;
        this.qty = qty;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
