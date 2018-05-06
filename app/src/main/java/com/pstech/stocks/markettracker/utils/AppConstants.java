package com.pstech.stocks.markettracker.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
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
    private static final String NA = "Not Available";

    public static boolean isEmpty(String value) {
        if (value == null || value.isEmpty())
            return true;
        return false;
    }
    public static String processString(String value) {
        if (isEmpty(value)) {
            return NA;
        }
        return value;
    }
    public static String processInt(int value) {
        if (value == 0)
            return NA;
        return String.valueOf(value);
    }

    public static String makeRange(String value1, String value2) {
        return value1 + DASH + value2;
    }

    public static String getTodayDate() {

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date date = new Date();
        return formatter.format(date);
    }

    public static void loadImage(Context context, ImageView imageView, String imagePath) {
        if (!AppConstants.isEmpty(imagePath)) {
            Glide.with(context)
                    .load(imagePath)
                    .into(imageView);
        }
    }


}
