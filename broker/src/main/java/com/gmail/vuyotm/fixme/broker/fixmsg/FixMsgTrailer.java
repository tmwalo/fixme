package com.gmail.vuyotm.fixme.broker.fixmsg;

public class FixMsgTrailer {

    private static final int        MOD_VAL = 256;

    private String                  fixMsgHeaderAndContent;
    private int                     checksum;
    private String                  checksumStr = "";

    public void setFixMsgHeaderAndContent(String fixMsgHeaderAndContent) {
        this.fixMsgHeaderAndContent = fixMsgHeaderAndContent;
    }

    private void setChecksum() {
        int     asciiSum;
        char    currentChar;
        int     currentCharAscii;

        asciiSum = 0;
        for (int index = 0; index < fixMsgHeaderAndContent.length(); ++index) {
            currentChar = fixMsgHeaderAndContent.charAt(index);
            currentCharAscii = (int) currentChar;
            asciiSum += currentCharAscii;
        }
        checksum = asciiSum % MOD_VAL;
        checksumStr = "";
        checksumStr += FixMsg.CHECKSUM_TAG;
        checksumStr += FixMsg.TAG_VAL_LINK;
        checksumStr += String.format("%03d", checksum);
        checksumStr += FixMsg.TAG_VAL_SEPARATOR;
    }

    public String getChecksumStr() {
        if (checksumStr.equals(""))
            setChecksum();
        return checksumStr;
    }

}
