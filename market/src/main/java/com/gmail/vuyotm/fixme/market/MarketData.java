package com.gmail.vuyotm.fixme.market;

import java.util.HashMap;

public class MarketData {

    private static String               marketId;
    private HashMap<String, Stock>      stocks;

    public MarketData() {
        stocks = new HashMap<>();
        initStocks();
    }

    private void initStocks() {
        stocks.put("NVDA", new Stock("NVDA", 244.49f, 100));
        stocks.put("AMZN", new Stock("AMZN", 1703.17f, 100));
        stocks.put("GOOGL", new Stock("GOOGL", 1140.34f, 100));
        stocks.put("FB", new Stock("FB", 199.27f, 100));
        stocks.put("NFLX", new Stock("NFLX", 409.46f, 100));
        stocks.put("MU", new Stock("MU", 54.85f, 100));
        stocks.put("AAPL", new Stock("AAPL", 186.40f, 100));
        stocks.put("MSFT", new Stock("MSFT", 99.53f, 100));
        stocks.put("CRM", new Stock("CRM", 135.62f, 100));
        stocks.put("TSLA", new Stock("TSLA", 260f, 100));
    }

    public static String getMarketId() {
        return marketId;
    }

    public static void setMarketId(String marketId) {
        MarketData.marketId = marketId;
    }

    public HashMap<String, Stock> getStocks() {
        return stocks;
    }

}
