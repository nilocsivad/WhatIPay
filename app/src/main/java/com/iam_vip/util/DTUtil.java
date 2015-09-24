package com.iam_vip.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Colin on 2015/8/26.
 */
@SuppressLint("SimpleDateFormat")
public final class DTUtil {

    public static final String FMT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String FMT_DATE = "yyyy-MM-dd";

    public static final DateFormat DT_FMT_DEFAULT = new SimpleDateFormat( FMT_DEFAULT );
    public static final DateFormat DT_FMT_DATE = new SimpleDateFormat( FMT_DATE );

    public static String nowDT() {
        return DT_FMT_DEFAULT.format( new Date() );
    }

    public static String parseDateFmt( Date date ) {
        return DT_FMT_DATE.format( date );
    }

}
