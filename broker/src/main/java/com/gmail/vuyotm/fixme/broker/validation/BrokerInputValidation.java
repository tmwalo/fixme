package com.gmail.vuyotm.fixme.broker.validation;

public class BrokerInputValidation {

    private static final int        MARKET_ID_MIN = 100_000;
    private static final int        MARKET_ID_MAX = 999_999;
    private static final int        ORDER_QTY_MIN = 0;
    private static final int        ORDER_QTY_MAX = 1_000_000_000;
    private static final String     MARKET_ID_MIN_STR = Integer.toString(MARKET_ID_MIN);
    private static final String     MARKET_ID_MAX_STR = Integer.toString(MARKET_ID_MAX);
    private static final String     ORDER_QTY_MIN_STR = Integer.toString(ORDER_QTY_MIN);
    private static final String     ORDER_QTY_MAX_STR = Integer.toString(ORDER_QTY_MAX);
    private static final String     QUIT = "q";

    public static boolean isValidStr(String brokerInput) {
        if ((brokerInput == null) || (brokerInput.length() == 0) || (brokerInput.equals("")))
            return (false);
        else
            return (true);
    }

    public static boolean isMarketId(String brokerInput) {
        int     marketId;

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
            System.out.println("Error: Market Id must be between " + MARKET_ID_MIN_STR + " - " + MARKET_ID_MAX_STR + ".");
            return (false);
        }
        return (true);
    }

    public static boolean isValidQty(String brokerInput) {
        int     quantity;

        if (!isValidStr(brokerInput))
            return (false);
        try {
            quantity = Integer.parseInt(brokerInput);
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Quantity is invalid - it must be a number.");
            return (false);
        }
        if ((quantity < ORDER_QTY_MIN) || (quantity > ORDER_QTY_MAX)) {
            System.out.println("Error: Quantity is invalid - it must be between " + ORDER_QTY_MIN_STR + " - " + ORDER_QTY_MAX_STR + ".");
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

        tokens = brokerInput.split(" ");
        if ((tokens.length == 4) && ((tokens[0]).equals("buy")) && isValidQty(tokens[1]) && isValidStr(tokens[2]) && isMarketId(tokens[3]))
            return (true);
        else
            return (false);
    }

    public static boolean isSellOrder(String brokerInput) {
        String[]    tokens;

        tokens = brokerInput.split(" ");
        if ((tokens.length == 4) && ((tokens[0]).equals("sell")) && isValidQty(tokens[1]) && isValidStr(tokens[2]) && isMarketId(tokens[3]))
            return (true);
        else
            return (false);
    }

    public static boolean isQuit(String brokerInput) {
        if (!isValidStr(brokerInput))
            return (false);
        if (brokerInput.equals(QUIT))
            return (true);
        else
            return (false);
    }

}
