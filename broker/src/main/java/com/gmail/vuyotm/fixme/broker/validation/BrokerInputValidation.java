package com.gmail.vuyotm.fixme.broker.validation;

public class BrokerInputValidation {

    private static final int    MARKET_ID_MIN = 100_000;
    private static final int    MARKET_ID_MAX = 999_999;
    private static final int    ORDER_QTY_MIN = 0;
    private static final int    ORDER_QTY_MAX = 1_000_000_000;

    public static boolean isValidStr(String brokerInput) {
        if ((brokerInput == null) || (brokerInput.length() == 0) || (brokerInput.equals("")))
            return (false);
        else
            return (true);
    }

    public static boolean isMarketId(String brokerInput) {
        int         marketId;

        if (!isValidStr(brokerInput))
            return (false);
        try {
            marketId = Integer.parseInt(brokerInput);
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Market Id invalid - it must be a number.");
            return (false);
        }
        if ((marketId < MARKET_ID_MIN) || (marketId > MARKET_ID_MAX)) {
            System.out.println("Error: Market Id out of range.");
            return (false);
        }
        return (true);
    }

    public static boolean isListMarkets(String brokerInput) {
        if (!isValidStr(brokerInput))
            return (false);
        if (brokerInput.equals("list markets"))
            return (true);
        else
            return (false);
    }

    public static boolean isListMarket(String brokerInput) {
        String[]    tokens;

        if (!isValidStr(brokerInput))
            return (false);
        tokens = brokerInput.split(" ");
        if ((tokens.length == 2) && ((tokens[0]).equals("list")) && isMarketId(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isBuyOrder(String brokerInput) {
        String[]    tokens;
        int         quantity;

        if (!isValidStr(brokerInput))
            return (false);
        tokens = brokerInput.split(" ");
        try {
            quantity = Integer.parseInt(tokens[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Quantity is invalid - it must be a number.");
            return (false);
        }
        if ((quantity < ORDER_QTY_MIN) || (quantity > ORDER_QTY_MAX)) {
            System.out.println("Error: Quantity is invalid - it must be a positive number.");
            return (false);
        }
        if ((tokens.length == 4) && ((tokens[0]).equals("buy")) && isValidStr(tokens[2]) && isMarketId(tokens[3]))
            return (true);
        else
            return (false);
    }

    public static boolean isSellOrder(String brokerInput) {
        String[]    tokens;
        int         quantity;

        if (!isValidStr(brokerInput))
            return (false);
        tokens = brokerInput.split(" ");
        try {
            quantity = Integer.parseInt(tokens[1]);
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Quantity is invalid - it must be a number.");
            return (false);
        }
        if ((quantity < ORDER_QTY_MIN) || (quantity > ORDER_QTY_MAX)) {
            System.out.println("Error: Quantity is invalid - it must be a positive number.");
            return (false);
        }
        if ((tokens.length == 4) && ((tokens[0]).equals("sell")) && isValidStr(tokens[2]) && isMarketId(tokens[3]))
            return (true);
        else
            return (false);
    }

    public static boolean isQuit(String brokerInput) {
        if (!isValidStr(brokerInput))
            return (false);
        if (brokerInput.equals("q"))
            return (true);
        else
            return (false);
    }

}
