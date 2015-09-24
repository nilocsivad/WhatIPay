package com.iam_vip.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Colin on 2015/8/26.
 */
public final class AppToast {

    private static Context context;

    public static void init( Context context ) {
        AppToast.context = context;
    }

    public static void ShowShort( int resId ) {
        ShowShort( AppHelper.STR( resId ) );
    }

    public static void ShowShort( CharSequence txt ) {
        Toast.makeText( context, txt, Toast.LENGTH_SHORT ).show();
    }

    public static void ShowLong( int resId ) {
        ShowLong( AppHelper.STR( resId ) );
    }

    public static void ShowLong( CharSequence txt ) {
        Toast.makeText( context, txt, Toast.LENGTH_LONG ).show();
    }

}
