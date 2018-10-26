package com.gmail.vuyotm.fixme.market;

import com.gmail.vuyotm.fixme.core.FixMsg;
import com.gmail.vuyotm.fixme.core.RequestValidation;

import java.util.HashMap;

public class MarketDataValidation {

    public static boolean isListMarket(String request) {
        String[]    tokens;

        if (!RequestValidation.isValidStr(request))
            return (false);
        tokens = request.split(" ");
        if (RequestValidation.isListMarket(request) && (tokens[2]).equals(MarketData.getMarketId()))
            return (true);
        else
            return (false);
    }

    public static boolean isTargetCompIdTagVal(String request) {
        String[]    tokens;

        if (!RequestValidation.isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if (RequestValidation.isTargetCompIdTagVal(request) && (tokens[1]).equals(MarketData.getMarketId()))
            return (true);
        else
            return (false);
    }

    public static boolean isTickerSymbolTagVal(String request, HashMap tickerSymbols) {
        String[]    tokens;

        if (!RequestValidation.isValidStr(request) || (tickerSymbols == null))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if (RequestValidation.isTickerSymbolTagVal(request) && tickerSymbols.containsKey(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isBuyOrder(String request) {
        String[]    tokens;
        MarketData  stockMarketData;

        if (!RequestValidation.isValidStr(request))
            return (false);
        stockMarketData = MarketData.getInstance();
        tokens = request.split("\\" + FixMsg.TAG_VAL_SEPARATOR);
        if (RequestValidation.isBuyOrder(request) && isTargetCompIdTagVal(tokens[4]) && isTickerSymbolTagVal(tokens[9], stockMarketData.getStocks())) {
            return (true);
        }
        else {
            return (false);
        }
    }

    public static boolean isSellOrder(String request) {
        String[]    tokens;
        MarketData  stockMarketData;

        if (!RequestValidation.isValidStr(request))
            return (false);
        stockMarketData = MarketData.getInstance();
        tokens = request.split("\\" + FixMsg.TAG_VAL_SEPARATOR);
        if (RequestValidation.isSellOrder(request) && isTargetCompIdTagVal(tokens[4]) && isTickerSymbolTagVal(tokens[9], stockMarketData.getStocks())) {
            return (true);
        }
        else {
            return (false);
        }
    }

}
