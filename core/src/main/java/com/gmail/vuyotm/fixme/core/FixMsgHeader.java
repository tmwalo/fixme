package com.gmail.vuyotm.fixme.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class FixMsgHeader {

    private int                     bodyLen;
    private static int              msgSeqNum = 0;

    private static final String     beginStr = "FIX.4.0";
    private String                  bodyLenStr;
    private String                  msgType;
    private final String            senderCompId;
    private final String            targetCompId;
    private final String            msgSeqNumStr;
    private String                  sendingTimeStr;
    private String                  beginStrHeader;
    private String                  bodyLenHeader;
    private String                  header;

    public FixMsgHeader(String senderCompId, String targetCompId) {
        this.senderCompId = senderCompId;
        this.targetCompId = targetCompId;
        ++msgSeqNum;
        msgSeqNumStr = Integer.toString(msgSeqNum);
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    private void setBodyLen(int bodyLen) {
        this.bodyLen = bodyLen;
        bodyLenStr = Integer.toString(this.bodyLen);
    }

    private void setSendingTimeStr() {
        Date                currentDate;
        SimpleDateFormat    simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
        currentDate = new Date();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        sendingTimeStr = simpleDateFormat.format(currentDate);
    }

    public void setBodyLenHeader() {
        setSendingTimeStr();
        bodyLenHeader = "";
        bodyLenHeader += FixMsg.MSG_TYPE_TAG;
        bodyLenHeader += FixMsg.TAG_VAL_LINK;
        bodyLenHeader += msgType;
        bodyLenHeader += FixMsg.TAG_VAL_SEPARATOR;
        bodyLenHeader += FixMsg.SENDER_COMP_ID_TAG;
        bodyLenHeader += FixMsg.TAG_VAL_LINK;
        bodyLenHeader += senderCompId;
        bodyLenHeader += FixMsg.TAG_VAL_SEPARATOR;
        bodyLenHeader += FixMsg.TARGET_COMP_ID_TAG;
        bodyLenHeader += FixMsg.TAG_VAL_LINK;
        bodyLenHeader += targetCompId;
        bodyLenHeader += FixMsg.TAG_VAL_SEPARATOR;
        bodyLenHeader += FixMsg.MSG_SEQ_NUM_TAG;
        bodyLenHeader += FixMsg.TAG_VAL_LINK;
        bodyLenHeader += msgSeqNumStr;
        bodyLenHeader += FixMsg.TAG_VAL_SEPARATOR;
        bodyLenHeader += FixMsg.SENDING_TIME_TAG;
        bodyLenHeader += FixMsg.TAG_VAL_LINK;
        bodyLenHeader += sendingTimeStr;
        bodyLenHeader += FixMsg.TAG_VAL_SEPARATOR;
    }

    public String getBodyLenHeader() {
        return (bodyLenHeader);
    }

    public void setBeginStrHeader(int bodyLen) {
        setBodyLen(bodyLen);
        beginStrHeader = "";
        beginStrHeader += FixMsg.BEGIN_STR_TAG;
        beginStrHeader += FixMsg.TAG_VAL_LINK;
        beginStrHeader += beginStr;
        beginStrHeader += FixMsg.TAG_VAL_SEPARATOR;
        beginStrHeader += FixMsg.BODY_LEN_TAG;
        beginStrHeader += FixMsg.TAG_VAL_LINK;
        beginStrHeader += bodyLenStr;
        beginStrHeader += FixMsg.TAG_VAL_SEPARATOR;
    }

    public String getBeginStrHeader() {
        return beginStrHeader;
    }

    public void setHeader() {
        header = getBeginStrHeader();
        header += getBodyLenHeader();
    }

    public String getHeader() {
        return header;
    }

}
