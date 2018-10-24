package com.gmail.vuyotm.fixme.broker;

public class BrokerData {

    private static int      msgNum = 0;
    private static String   brokerId;

    public static String getCliOrdId() {
        String  msgNumStr;
        String  cliOrdId;

        ++msgNum;
        msgNumStr = Integer.toString(msgNum);
        cliOrdId = msgNumStr;
        return (cliOrdId);
    }

    public static void setBrokerId(String brokerId) {
        BrokerData.brokerId = brokerId;
    }

    public static String getBrokerId() {
        return brokerId;
    }

}