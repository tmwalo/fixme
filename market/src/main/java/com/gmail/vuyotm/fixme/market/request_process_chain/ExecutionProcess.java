package com.gmail.vuyotm.fixme.market.request_process_chain;

import com.gmail.vuyotm.fixme.market.Request;
import com.gmail.vuyotm.fixme.market.RequestExecutor;
import com.gmail.vuyotm.fixme.market.ResponseGenerator;

public class ExecutionProcess extends ProcessChain {

    @Override
    public void execute(Request request) {
        String  marketResponse;

        if (request.getRequestType().equals(Request.LIST_MARKETS)) {
            marketResponse = RequestExecutor.listMarkets(request.getRequest());
        }
        else if (request.getRequestType().equals(Request.LIST_MARKET_ID)) {
            marketResponse = RequestExecutor.listMarketId(request.getRequest());
        }
        else if (request.getRequestType().equals(Request.BUY_ORDER)) {
            marketResponse = RequestExecutor.buyOrder(request.getRequest());
        }
        else if (request.getRequestType().equals(Request.SELL_ORDER)) {
            marketResponse = RequestExecutor.sellOrder(request.getRequest());
        }
        else {
            marketResponse = ResponseGenerator.reject(request.getRequest());
        }
        request.setResponse(marketResponse);
        if (this.getNextProcess() != null)
            this.getNextProcess().execute(request);
    }

}
