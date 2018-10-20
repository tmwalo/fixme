package com.gmail.vuyotm.fixme.broker;

import java.util.Date;

public class BrokerData {

    private static int      msgNum = 0;
    private static String   brokerId;

    public static String getCliOrdId() {
        Date    currentDateTime;
        String  msgNumStr;
        String  cliOrdId;

        ++msgNum;
        msgNumStr = Integer.toString(msgNum);
        currentDateTime = new Date();
        cliOrdId = msgNumStr + "-" + currentDateTime.toString();
        return (cliOrdId);
    }

    public static void setBrokerId(String brokerId) {
        BrokerData.brokerId = brokerId;
    }

    public static String getBrokerId() {
        return brokerId;
    }

}