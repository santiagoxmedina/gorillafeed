package com.sanmed.gorillabook.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String patternDate = "EEEE ,MMMM d";
    public static final String pattern2Date = "mm/dd/YYYY";

    public static String stringDate(Date date,String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
        return df.format(date);
    }
}
