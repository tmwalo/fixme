package com.gmail.vuyotm.fixme.core;

public class FixMsgExecute extends FixMsgType {

    private static final String     MSG_TYPE = "8";
    private static int              execId = 0;

    private final String            orderId;
    private final String            execTransType = FixMsg.EXEC_TRANS_TYPE_VAL_NEW;
    private final String            orderStatus = FixMsg.ORDER_STATUS_FILLED;
    private final String            tickerSymbol;
    private final String            side;
    private final int               orderQty;
    private String                  orderQtyStr;
    private final int               cumQty;
    private String                  cumQtyStr;
    private final float             avgPx;
    private String                  avgPxStr;
    private String                  fixMsgExec;

    public FixMsgExecute(FixMsgHeader fixMsgHeader, FixMsgTrailer fixMsgTrailer, String orderId, String tickerSymbol, String side, int orderQty, int cumQty, float avgPx) {
        super(fixMsgHeader, fixMsgTrailer);
        this.orderId = orderId;
        this.tickerSymbol = tickerSymbol;
        this.side = side;
        this.orderQty = orderQty;
        orderQtyStr = Integer.toString(this.orderQty);
        this.cumQty = cumQty;
        cumQtyStr = Integer.toString(this.cumQty);
        this.avgPx = avgPx;
        avgPxStr = Float.toString(this.avgPx);
        ++execId;
        getFixMsgHeader().setMsgType(MSG_TYPE);
    }

    public void setFixMsgExec() {
        FixMsgHeader    fixMsgHeader;
        FixMsgTrailer   fixMsgTrailer;
        int             bodyLen;

        fixMsgHeader = getFixMsgHeader();
        fixMsgHeader.setBodyLenHeader();
        fixMsgExec = fixMsgHeader.getBodyLenHeader();
        fixMsgExec += FixMsg.ORDER_ID_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += orderId;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgExec += FixMsg.EXEC_ID_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += execId;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgExec += FixMsg.EXEC_TRANS_TYPE_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += execTransType;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgExec += FixMsg.ORDER_STATUS_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += orderStatus;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgExec += FixMsg.SYMBOL_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += tickerSymbol;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgExec += FixMsg.SIDE_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += side;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgExec += FixMsg.ORDER_QTY_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += orderQtyStr;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgExec += FixMsg.CUM_QTY_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += cumQtyStr;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgExec += FixMsg.AVG_PX_TAG;
        fixMsgExec += FixMsg.TAG_VAL_LINK;
        fixMsgExec += avgPxStr;
        fixMsgExec += FixMsg.TAG_VAL_SEPARATOR;
        bodyLen = fixMsgExec.length();
        fixMsgHeader.setBeginStrHeader(bodyLen);
        fixMsgExec = fixMsgHeader.getBeginStrHeader() + fixMsgExec;
        fixMsgTrailer = getFixMsgTrailer();
        fixMsgTrailer.setFixMsgHeaderAndContent(fixMsgExec);
        fixMsgExec += fixMsgTrailer.getChecksumStr();
    }

    public String getFixMsgExec() {
        return fixMsgExec;
    }

}
