package com.pstech.stocks.buybackipostockmarket.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by pagrawal on 04-01-2018.
 */

public class AppConstants {
    public static final String SHARE_TXT = "Hey check out my app at: https://play.google.com/store/apps/details?id=com.pstech.hydmetro";
    public static final String IPO_SCRIP = "IPO_SCRIP";
    public static final String BUYBACK_SCRIP = "BUYBACK_SCRIP";

    public static final String ANONYMOUS = "anonymous";
    public static final String TAG = "SignInLog";
    private static final String DASH = " - ";

    private static final java.lang.String DATE_FORMAT = "yyyy-MM-dd";
    public static final String FUTURE_DATE = "2100-12-31";

    public static boolean isEmpty(String value) {
        if (value == null || value.isEmpty())
            return true;
        return false;
    }
    public static String processString(String value) {
        if (isEmpty(value)) {
            return "NA";
        }
        return value;
    }

    public static String makeRange(String value1, String value2) {
        return value1 + DASH + value2;
    }

    public static String getTodayDate() {

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date date = new Date();
        return formatter.format(date);
    }


}
