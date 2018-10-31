package com.gmail.vuyotm.fixme.market;

public class RequestExecutor {

    public static String listMarkets(String request) {
        String  response;

        response = ResponseGenerator.listMarkets(request);
        return (response);
    }

    public static String listMarketId(String request) {
        String  response;

        response = ResponseGenerator.listMarketId(request);
        return (response);
    }

    public static String buyOrder(String request) {
        String      tickerSymbol;
        int         orderedQty;
        String      orderedQtyStr;
        int         availableQty;
        MarketData  marketData;
        Stock       stock;
        String      response;

        tickerSymbol = ResponseGenerator.extractTickerSymbol(request);
        orderedQtyStr = ResponseGenerator.extractOrdQty(request);
        orderedQty = Integer.parseInt(orderedQtyStr);
        marketData = MarketData.getInstance();
        stock = marketData.getStocks().get(tickerSymbol);
        availableQty = stock.getQty();
        if (availableQty >= orderedQty) {
            stock.setQty(stock.getQty() - orderedQty);
            response = ResponseGenerator.execute(request);
        }
        else {
            response = ResponseGenerator.reject(request);
        }
        return (response);
    }

    public static String sellOrder(String request) {
        String      tickerSymbol;
        int         saleQty;
        String      saleQtyStr;
        MarketData  marketData;
        Stock       stock;
        String      response;

        tickerSymbol = ResponseGenerator.extractTickerSymbol(request);
        saleQtyStr = ResponseGenerator.extractOrdQty(request);
        saleQty = Integer.parseInt(saleQtyStr);
        marketData = MarketData.getInstance();
        stock = marketData.getStocks().get(tickerSymbol);
        stock.setQty(stock.getQty() + saleQty);
        response = ResponseGenerator.execute(request);
        return (response);
    }

}
