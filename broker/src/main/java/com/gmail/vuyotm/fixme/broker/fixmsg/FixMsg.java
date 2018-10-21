package com.gmail.vuyotm.fixme.broker.fixmsg;

public class FixMsg {

    public static final String      ORDER_MSG = "order";
    public static final String      EXECUTE_MSG = "execute";
    public static final String      REJECT_MSG = "reject";

    public static final String      TAG_VAL_SEPARATOR = "|";
    public static final String      TAG_VAL_LINK = "=";

    public static final String      BEGIN_STR_TAG = "8";
    public static final String      BODY_LEN_TAG = "9";
    public static final String      MSG_TYPE_TAG = "35";
    public static final String      SENDER_COMP_ID_TAG = "49";
    public static final String      TARGET_COMP_ID_TAG = "56";
    public static final String      MSG_SEQ_NUM_TAG = "34";
    public static final String      SENDING_TIME_TAG = "52";
    public static final String      CHECKSUM_TAG = "10";
    public static final String      CL_ORD_ID_TAG = "11";
    public static final String      HANDL_INST_TAG = "21";
    public static final String      SYMBOL_TAG = "55";
    public static final String      SIDE_TAG = "54";
    public static final String      ORDER_QTY_TAG = "38";
    public static final String      ORDER_TYPE_TAG = "40";
    public static final String      ORDER_ID_TAG = "37";
    public static final String      EXEC_ID_TAG = "17";
    public static final String      EXEC_TRANS_TYPE_TAG = "20";
    public static final String      ORDER_STATUS_TAG = "39";
    public static final String      CUM_QTY_TAG = "14";
    public static final String      AVG_PX_TAG = "6";
    public static final String      REF_SEQ_NUM_TAG = "45";

    public static final String      AUTO_EXEC_PRIVATE_NO_BROKER = "1";
    public static final String      BUY = "1";
    public static final String      SELL = "2";
    public static final String      MARKET_ORDER_TYPE = "1";

}
