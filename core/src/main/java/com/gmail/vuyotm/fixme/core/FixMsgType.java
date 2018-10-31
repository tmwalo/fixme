package com.gmail.vuyotm.fixme.core;

public abstract class FixMsgType {

    private final FixMsgHeader      fixMsgHeader;
    private final FixMsgTrailer     fixMsgTrailer;
    private static int              orderId = 0;

    public FixMsgType(FixMsgHeader fixMsgHeader, FixMsgTrailer fixMsgTrailer) {
        this.fixMsgHeader = fixMsgHeader;
        this.fixMsgTrailer = fixMsgTrailer;
    }

    public FixMsgHeader getFixMsgHeader() {
        return fixMsgHeader;
    }

    public FixMsgTrailer getFixMsgTrailer() {
        return fixMsgTrailer;
    }

    public static int getOrderId() {
        ++orderId;
        return (orderId);
    }

}
