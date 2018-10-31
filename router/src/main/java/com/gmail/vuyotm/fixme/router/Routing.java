package com.gmail.vuyotm.fixme.router;

import com.gmail.vuyotm.fixme.core.*;

import java.util.HashMap;

public class Routing {

    public static String extractTargetIdListMarketReq(String request) {
        String[]    tokens;

        tokens = request.split(" ");
        return (tokens[2]);
    }

    public static String extractSenderIdListMarketsReq(String request) {
        String[]    tokens;

        tokens = request.split(" ");
        return (tokens[0]);
    }

    public static String extractTargetIdListMarketsResp(String request) {
        String[]    tokens;

        tokens = request.split(" ");
        return (tokens[0]);
    }

    public static String extractTargetIdListMarketResp(String request) {
        String[]    tokens;

        tokens = request.split(" ");
        return (tokens[0]);
    }

    public static String extractTargetId(String request) {
        String[]    tokens;

        tokens = request.split(" ");
        return (tokens[0]);
    }

    public static String extractSenderCompId(String request) {
        int         startIndex;
        int         endIndex;
        String      senderCompIdTagVal;
        String[]    tokens;

        startIndex = request.indexOf(FixMsg.SENDER_COMP_ID_TAG + FixMsg.TAG_VAL_LINK);
        endIndex = request.indexOf(FixMsg.TAG_VAL_SEPARATOR, startIndex);
        senderCompIdTagVal = request.substring(startIndex, endIndex);
        tokens = senderCompIdTagVal.split(FixMsg.TAG_VAL_LINK);
        return (tokens[1]);
    }

    public static String extractTargetCompId(String request) {
        int         startIndex;
        int         endIndex;
        String      senderCompIdTagVal;
        String[]    tokens;

        startIndex = request.indexOf(FixMsg.TARGET_COMP_ID_TAG + FixMsg.TAG_VAL_LINK);
        endIndex = request.indexOf(FixMsg.TAG_VAL_SEPARATOR, startIndex);
        senderCompIdTagVal = request.substring(startIndex, endIndex);
        tokens = senderCompIdTagVal.split(FixMsg.TAG_VAL_LINK);
        return (tokens[1]);
    }

    public static String getBuffStr(Attachment attachment) {
        int         limits;
        byte[]      bytes;
        String      msg;

        if (attachment == null)
            return (null);
        attachment.getBuffer().flip();
        limits = attachment.getBuffer().limit();
        bytes = new byte[limits];
        attachment.getBuffer().get(bytes, 0, limits);
        attachment.getBuffer().clear();
        msg = new String(bytes);
        return (msg);
    }

    public static String getTargetId(String request) {
        String      targetId;

        targetId = null;
        if (RequestValidation.isFixMsg(request)) {
            targetId = extractTargetCompId(request);
        }
        else if (RequestValidation.isListReqOrResp(request)) {
            if (RequestValidation.isListMarkets(request)) {
                String      senderId;

                senderId = extractSenderIdListMarketsReq(request);
                targetId = senderId;
            }
            else if (RequestValidation.isListMarketsResponse(request)) {
                targetId = Routing.extractTargetIdListMarketsResp(request);
            }
            else if (RequestValidation.isListMarket(request)) {
                targetId = Routing.extractTargetIdListMarketReq(request);
            }
            else if (RequestValidation.isListMarketResponse(request)) {
                targetId = Routing.extractTargetIdListMarketResp(request);
            }
        }
        return (targetId);
    }

    public static String getSenderId(String request) {
        String  senderId;

        senderId = null;
        if (RequestValidation.isFixMsg(request)) {
            senderId = extractSenderCompId(request);
        }
        else if (RequestValidation.isListReqOrResp(request)) {
            senderId = extractTargetId(request);
        }
        return (senderId);
    }

    public static Attachment processRequest(String request) {
        String                          targetId;
        RoutingTable                    routingTable;
        Attachment                      attachment;
        HashMap<String, Attachment>     markets;

        routingTable = RoutingTable.getInstance();
        targetId = getTargetId(request);
        attachment = routingTable.getEntry(targetId);
        if (attachment == null) {
            String      senderId;

            senderId = getSenderId(request);
            attachment = routingTable.getEntry(senderId);
        }
        else if (RequestValidation.isListMarkets(request)) {
            String      listMarketsResp;
            byte[]      byteListMarketsResp;

            listMarketsResp = "";
            listMarketsResp += targetId + " ";
            markets = routingTable.getMarkets();
            for (String key : markets.keySet()) {
                listMarketsResp += key + " ";
            }
            listMarketsResp = listMarketsResp.trim();

            attachment.getBuffer().clear();
            byteListMarketsResp = listMarketsResp.getBytes();
            attachment.getBuffer().put(byteListMarketsResp);
            attachment.getBuffer().flip();
        }
        return (attachment);
    }

}
