package com.gmail.vuyotm.fixme.market;

public class Request {

    public static final String      LIST_MARKETS = "list markets";
    public static final String      LIST_MARKET_ID = "list marketId";
    public static final String      BUY_ORDER = "buy order";
    public static final String      SELL_ORDER = "sell order";
    public static final String      UNKNOWN = "unkown";

    private Attachment              attachment;
    private final String            request;
    private String                  requestType;
    private String                  response;

    public Request(Attachment attachment, String request) {
        this.attachment = attachment;
        this.request = request;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public String getRequest() {
        return request;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
