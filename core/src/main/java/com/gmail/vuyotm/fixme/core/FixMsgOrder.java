package com.gmail.vuyotm.fixme.core;

public class FixMsgOrder extends FixMsgType {

    private static final String     MSG_TYPE = "D";

    private final String            clOrdId;
    private final String            handlInst;
    private final String            tickerSymbol;
    private final String            side;
    private final int               orderQty;
    private String                  orderQtyStr;
    private final String            orderType;
    private String                  fixMsgOrder;

    public FixMsgOrder(FixMsgHeader fixMsgHeader, FixMsgTrailer fixMsgTrailer, String clOrdId, String handlInst, String tickerSymbol, String side, int orderQty, String orderType) {
        super(fixMsgHeader, fixMsgTrailer);
        this.clOrdId = clOrdId;
        this.handlInst = handlInst;
        this.tickerSymbol = tickerSymbol;
        this.side = side;
        this.orderQty = orderQty;
        orderQtyStr = Integer.toString(this.orderQty);
        this.orderType = orderType;
        getFixMsgHeader().setMsgType(MSG_TYPE);
    }

    public void setFixMsgOrder() {
        FixMsgHeader    fixMsgHeader;
        FixMsgTrailer   fixMsgTrailer;
        int             bodyLen;

        fixMsgHeader = getFixMsgHeader();
        fixMsgHeader.setBodyLenHeader();
        fixMsgOrder = fixMsgHeader.getBodyLenHeader();
        fixMsgOrder += FixMsg.CL_ORD_ID_TAG;
        fixMsgOrder += FixMsg.TAG_VAL_LINK;
        fixMsgOrder += clOrdId;
        fixMsgOrder += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgOrder += FixMsg.HANDL_INST_TAG;
        fixMsgOrder += FixMsg.TAG_VAL_LINK;
        fixMsgOrder += handlInst;
        fixMsgOrder += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgOrder += FixMsg.SYMBOL_TAG;
        fixMsgOrder += FixMsg.TAG_VAL_LINK;
        fixMsgOrder += tickerSymbol;
        fixMsgOrder += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgOrder += FixMsg.SIDE_TAG;
        fixMsgOrder += FixMsg.TAG_VAL_LINK;
        fixMsgOrder += side;
        fixMsgOrder += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgOrder += FixMsg.ORDER_QTY_TAG;
        fixMsgOrder += FixMsg.TAG_VAL_LINK;
        fixMsgOrder += orderQtyStr;
        fixMsgOrder += FixMsg.TAG_VAL_SEPARATOR;
        fixMsgOrder += FixMsg.ORDER_TYPE_TAG;
        fixMsgOrder += FixMsg.TAG_VAL_LINK;
        fixMsgOrder += orderType;
        fixMsgOrder += FixMsg.TAG_VAL_SEPARATOR;
        bodyLen = fixMsgOrder.length();
        fixMsgHeader.setBeginStrHeader(bodyLen);
        fixMsgOrder = fixMsgHeader.getBeginStrHeader() + fixMsgOrder;
        fixMsgTrailer = getFixMsgTrailer();
        fixMsgTrailer.setFixMsgHeaderAndContent(fixMsgOrder);
        fixMsgOrder += fixMsgTrailer.getChecksumStr();
    }

    public String getFixMsgOrder() {
        return fixMsgOrder;
    }

}
