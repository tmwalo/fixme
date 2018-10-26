package com.gmail.vuyotm.fixme.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RequestValidation {

    private static final int        MARKET_ID_MIN = 100_000;
    private static final int        MARKET_ID_MAX = 999_999;
    private static final int        ORDER_QTY_MIN = 0;
    private static final int        ORDER_QTY_MAX = 1_000_000_000;
    private static final String     MARKET_ID_MIN_STR = Integer.toString(MARKET_ID_MIN);
    private static final String     MARKET_ID_MAX_STR = Integer.toString(MARKET_ID_MAX);
    private static final String     ORDER_QTY_MIN_STR = Integer.toString(ORDER_QTY_MIN);
    private static final String     ORDER_QTY_MAX_STR = Integer.toString(ORDER_QTY_MAX);
    private static final int        MOD_VAL = 256;

    private static final String     MARKET_DATA_LABEL = "MarketData";

    public static boolean isValidStr(String request) {
        if ((request == null) || (request.length() == 0) || (request.equals("")))
            return (false);
        else
            return (true);
    }

    public static boolean isMarketId(String request) {
        int     marketId;

        if (!isValidStr(request))
            return (false);
        try {
            marketId = Integer.parseInt(request);
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

    public static boolean isValidQty(String request) {
        int     quantity;

        if (!isValidStr(request))
            return (false);
        try {
            quantity = Integer.parseInt(request);
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

    public static boolean isListMarkets(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(" ");
        if ((tokens.length == 3) && isMarketId(tokens[0]) && (tokens[1]).equals("list") && (tokens[2]).equals("markets"))
            return (true);
        else
            return (false);
    }

    public static boolean isListMarketsResponse(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(" ");
        if ((tokens.length == 2) && isMarketId(tokens[0]) && isMarketId(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isListMarket(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(" ");
        if ((tokens.length == 3) && isMarketId(tokens[0]) && ((tokens[1]).equals("list")) && isMarketId(tokens[2]) )
            return (true);
        else
            return (false);
    }

    public static boolean isListMarketResponse(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(" ");
        if ((tokens.length >= 2) && isMarketId(tokens[0]) && (tokens[1]).equals(MARKET_DATA_LABEL))
            return (true);
        else
            return (false);
    }

    public static boolean isListReqOrResp(String request) {
        if (isListMarkets(request) || isListMarket(request) || isListMarketsResponse(request) || isListMarketResponse(request))
            return (true);
        else
            return (false);
    }

    public static boolean isBeginStrTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.BEGIN_STR_TAG)) && (tokens[1]).equals(FixMsg.FIX_VERSION))
            return (true);
        else
            return (false);
    }

    public static int fixMsgBodyLen(String request) {
        int     bodyLenTagIndex;
        int     checkSumTagIndex;
        int     nextSeparatorIndex;
        int     bodyStartIndex;
        int     bodyEndIndex;
        int     bodyLen;
        String  bodyLenStr;

        if (!isValidStr(request))
            return (-1);
        try {
            bodyLenTagIndex = request.indexOf(FixMsg.BODY_LEN_TAG + FixMsg.TAG_VAL_LINK);
            nextSeparatorIndex = request.indexOf(FixMsg.TAG_VAL_SEPARATOR, bodyLenTagIndex);
            bodyStartIndex = nextSeparatorIndex + 1;
            checkSumTagIndex = request.indexOf(FixMsg.CHECKSUM_TAG + FixMsg.TAG_VAL_LINK);
            bodyEndIndex = checkSumTagIndex - 1;
            bodyLenStr = request.substring(bodyStartIndex, bodyEndIndex + 1);
        }
        catch (IndexOutOfBoundsException e) {
            return (-1);
        }
        bodyLen = bodyLenStr.length();
        return (bodyLen);
    }

    public static boolean isBodyLenTagVal(String bodyLenTagVal, String request) {
        String[]    tokens;
        int         bodyLen;
        String      bodyLenStr;

        if (!isValidStr(bodyLenTagVal) || !isValidStr(request))
            return (false);
        tokens = bodyLenTagVal.split(FixMsg.TAG_VAL_LINK);
        bodyLen = fixMsgBodyLen(request);
        if (bodyLen == -1)
            return (false);
        bodyLenStr = Integer.toString(bodyLen);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.BODY_LEN_TAG)) && (tokens[1]).equals(bodyLenStr))
            return (true);
        else
            return (false);
    }

    public static boolean isMsgTypeTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.MSG_TYPE_TAG)) && (tokens[1]).equals(FixMsg.ORDER_MSG_TYPE))
            return (true);
        else
            return (false);
    }

    public static boolean isSenderCompIdTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.SENDER_COMP_ID_TAG)) && isMarketId(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isTargetCompIdTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.TARGET_COMP_ID_TAG)) && isMarketId(tokens[1]) )
            return (true);
        else
            return (false);
    }

    public static boolean isMsgSeqNum(String request) {
        int     quantity;

        if (!isValidStr(request))
            return (false);
        try {
            quantity = Integer.parseInt(request);
        }
        catch (NumberFormatException e) {
            return (false);
        }
        if (quantity < 1) {
            return (false);
        }
        return (true);
    }

    public static boolean isMsgSeqNumTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.MSG_SEQ_NUM_TAG)) && isMsgSeqNum(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isSendingTimeVal(String request) {
        if (!isValidStr(request))
            return (false);
        try {
            LocalDateTime.parse(request, DateTimeFormatter.ofPattern(FixMsg.SENDING_TIME_PATTERN));
            return (true);
        }
        catch (DateTimeParseException e) {

        }
        return (false);
    }

    public static boolean isSendingTimeTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.SENDING_TIME_TAG)) && isSendingTimeVal(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isCliOrdIdTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.CL_ORD_ID_TAG)) && isMsgSeqNum(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isHandlInstTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.HANDL_INST_TAG)) && (tokens[1]).equals(FixMsg.AUTO_EXEC_PRIVATE_NO_BROKER))
            return (true);
        else
            return (false);
    }

    public static boolean isTickerSymbolTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.SYMBOL_TAG)) && isValidStr(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isSideTagValBuy(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.SIDE_TAG)) && (tokens[1]).equals(FixMsg.BUY))
            return (true);
        else
            return (false);
    }

    public static boolean isSideTagValSell(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.SIDE_TAG)) && (tokens[1]).equals(FixMsg.SELL))
            return (true);
        else
            return (false);
    }

    public static boolean isOrderQtyTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.ORDER_QTY_TAG)) && isValidQty(tokens[1]))
            return (true);
        else
            return (false);
    }

    public static boolean isOrderTypeTagVal(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split(FixMsg.TAG_VAL_LINK);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.ORDER_TYPE_TAG)) && (tokens[1]).equals(FixMsg.MARKET_ORDER_TYPE))
            return (true);
        else
            return (false);
    }

    public static int calcCheckSum(String request) {
        int     checkSumTagIndex;
        int     checkSumStartIndex;
        int     checkSumEndIndex;
        String  checkSumStr;
        int     asciiSum;
        char    currentChar;
        int     currentCharAscii;
        int     checkSum;

        if (!isValidStr(request))
            return (-1);
        checkSumStartIndex = 0;
        try {
            checkSumTagIndex = request.indexOf(FixMsg.CHECKSUM_TAG + FixMsg.TAG_VAL_LINK);
            checkSumEndIndex = checkSumTagIndex - 1;
            checkSumStr = request.substring(checkSumStartIndex, checkSumEndIndex + 1);
        }
        catch (IndexOutOfBoundsException e) {
            return (-1);
        }
        asciiSum = 0;
        for (int index = 0; index < checkSumStr.length(); ++index) {
            currentChar = checkSumStr.charAt(index);
            currentCharAscii = (int) currentChar;
            asciiSum += currentCharAscii;
        }
        checkSum = asciiSum % MOD_VAL;
        return (checkSum);
    }

    public static boolean isCheckSumTagVal(String checkSumTagVal, String request) {
        String[]    tokens;
        int         checkSum;
        String      checkSumStr;

        if (!isValidStr(checkSumTagVal) || !isValidStr(request))
            return (false);
        tokens = checkSumTagVal.split(FixMsg.TAG_VAL_LINK);
        checkSum = calcCheckSum(request);
        if (checkSum == -1)
            return (false);
        checkSumStr = String.format("%03d", checkSum);
        if ((tokens.length == 2) && ((tokens[0]).equals(FixMsg.CHECKSUM_TAG)) && (tokens[1]).equals(checkSumStr))
            return (true);
        else
            return (false);
    }

    public static boolean isOrder(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split("\\" + FixMsg.TAG_VAL_SEPARATOR);
        if ((tokens.length == FixMsg.ORDER_MSG_REQUIRED_FIELDS)
                && isBeginStrTagVal(tokens[0])
                && isBodyLenTagVal(tokens[1], request)
                && isMsgTypeTagVal(tokens[2])
                && isSenderCompIdTagVal(tokens[3])
                && isTargetCompIdTagVal(tokens[4])
                && isMsgSeqNumTagVal(tokens[5])
                && isSendingTimeTagVal(tokens[6])
                && isCliOrdIdTagVal(tokens[7])
                && isHandlInstTagVal(tokens[8])
                && isTickerSymbolTagVal(tokens[9])
                && isValidStr(tokens[10])
                && isOrderQtyTagVal(tokens[11])
                && isOrderTypeTagVal(tokens[12])
                && isCheckSumTagVal(tokens[13], request)
                )
        {
            return (true);
        }
        else {
            return (false);
        }
    }

    public static boolean isBuyOrder(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split("\\" + FixMsg.TAG_VAL_SEPARATOR);
        if (isOrder(request) && isSideTagValBuy(tokens[10])) {
            return (true);
        }
        else {
            return (false);
        }
    }

    public static boolean isSellOrder(String request) {
        String[]    tokens;

        if (!isValidStr(request))
            return (false);
        tokens = request.split("\\" + FixMsg.TAG_VAL_SEPARATOR);
        if (isOrder(request) && isSideTagValSell(tokens[10])) {
            return (true);
        }
        else {
            return (false);
        }
    }

}
