package com.gmail.vuyotm.fixme.router;

import java.util.HashMap;

public class RoutingTable {

    private static RoutingTable             instance = null;
    private HashMap<String, Attachment>     routingTable;
    private HashMap<String, Attachment>     markets;

    private RoutingTable() {
        routingTable = new HashMap<>();
        markets = new HashMap<>();
    }

    public static RoutingTable getInstance() {
        if (instance == null)
            instance = new RoutingTable();
        return (instance);
    }

    private boolean isMarket(Attachment attachment) {
        if (attachment == null)
            return (false);
        if (attachment.getPort() == Router.MARKET_PORT)
            return (true);
        else
            return (false);
    }

    public void addEntry(Attachment attachment) {
        String  key;

        if (attachment == null)
            return ;
        key = attachment.getId();
        routingTable.put(key, attachment);
        if (isMarket(attachment))
            markets.put(key, attachment);
    }

    public void removeEntry(Attachment attachment) {
        String  key;

        if (attachment == null)
            return ;
        key = attachment.getId();
        if (isMarket(attachment))
            markets.remove(key);
        routingTable.remove(key);
    }

    public Attachment getEntry(String key) {
        return (routingTable.get(key));
    }

    public HashMap<String, Attachment> getMarkets() {
        return markets;
    }

}
