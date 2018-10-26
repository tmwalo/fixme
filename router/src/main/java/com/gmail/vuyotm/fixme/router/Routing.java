package com.gmail.vuyotm.fixme.router;

public class Routing {

    public static String extractTargetIdListMarketReq(String request) {
        String[]    tokens;

        tokens = request.split(" ");
        return (tokens[2]);
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

}
