package com.gmail.vuyotm.fixme.core;

public class FixMsgReject extends FixMsgType {

    private static final String     MSG_TYPE = "3";

    private final String            refSeqNum;
    private String                  fixMsgReject;

    public FixMsgReject(FixMsgHeader fixMsgHeader, FixMsgTrailer fixMsgTrailer, String refSeqNum) {
        super(fixMsgHeader, fixMsgTrailer);
        this.refSeqNum = refSeqNum;
        getFixMsgHeader().setMsgType(MSG_TYPE);
    }

    public String getFixMsgReject() {
        return fixMsgReject;
    }

    public void setFixMsgReject() {
        FixMsgHeader    fixMsgHeader;
        FixMsgTrailer   fixMsgTrailer;
        int             bodyLen;

        fixMsgHeader = getFixMsgHeader();
        fixMsgHeader.setBodyLenHeader();
        fixMsgReject = fixMsgHeader.getBodyLenHeader();
        fixMsgReject += FixMsg.REF_SEQ_NUM_TAG;
        fixMsgReject += FixMsg.TAG_VAL_LINK;
        fixMsgReject += refSeqNum;
        fixMsgReject += FixMsg.TAG_VAL_SEPARATOR;
        bodyLen = fixMsgReject.length();
        fixMsgHeader.setBeginStrHeader(bodyLen);
        fixMsgReject = fixMsgHeader.getBeginStrHeader() + fixMsgReject;
        fixMsgTrailer = getFixMsgTrailer();
        fixMsgTrailer.setFixMsgHeaderAndContent(fixMsgReject);
        fixMsgReject += fixMsgTrailer.getChecksumStr();
    }

}
