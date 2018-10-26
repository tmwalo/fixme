package com.gmail.vuyotm.fixme.market.request_process_chain;

import com.gmail.vuyotm.fixme.market.MarketDataValidation;
import com.gmail.vuyotm.fixme.market.Request;
import com.gmail.vuyotm.fixme.core.RequestValidation;

public class ValidationProcess extends ProcessChain {

    @Override
    public void execute(Request request) {
        String  requestType;

        if (RequestValidation.isListMarkets(request.getRequest())) {
            requestType = Request.LIST_MARKETS;
        }
        else if (MarketDataValidation.isListMarket(request.getRequest())) {
            requestType = Request.LIST_MARKET_ID;
        }
        else if (MarketDataValidation.isBuyOrder(request.getRequest())) {
            requestType = Request.BUY_ORDER;
        }
        else if (MarketDataValidation.isSellOrder(request.getRequest())) {
            requestType = Request.SELL_ORDER;
        }
        else {
            requestType = Request.UNKNOWN;
        }
        request.setRequestType(requestType);
        if (this.getNextProcess() != null)
            this.getNextProcess().execute(request);
    }

}
