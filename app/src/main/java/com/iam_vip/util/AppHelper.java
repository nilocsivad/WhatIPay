package com.iam_vip.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.iam_vip.c.C;

import java.io.File;

/**
 * Created by Colin on 2015/8/26.
 */
public final class AppHelper implements C {

    public static Context context;
    public static Resources resources;
    public static WindowManager windowManager;
    public static DisplayMetrics displayMetrics;

    /**
     * @param context
     */
    public static void init( Context context, WindowManager windowManager ) {
        AppHelper.context = context;
        AppHelper.resources = context.getResources();
        AppHelper.windowManager = windowManager;
        displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics( displayMetrics );

        AppToast.init( context );
    }

    /**
     * get system string resource
     *
     * @param resId
     * @return
     */
    public static String STR( int resId ) {
        return context.getString( resId );
    }

    /**
     * get system string array resource
     *
     * @param resId
     * @return
     */
    public static String[] STRARR( int resId ) {
        return resources.getStringArray( resId );
    }

    /**
     * get system integer resource
     *
     * @param resId
     * @return
     */
    public static int INT( int resId ) {
        return resources.getInteger( resId );
    }

    /**
     * get system integer array resource
     *
     * @param resId
     * @return
     */
    public static int[] INTARR( int resId ) {
        return resources.getIntArray( resId );
    }

    public static String APPFOLDER() {
        if ( Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED ) ) {
            File folder = new File( Environment.getExternalStorageDirectory(), APP_FOLDER );
            if ( !folder.exists() ) {
                folder.mkdirs();
            }
            return folder.getAbsolutePath();
        } else {
            return "";
        }
    }

    public static String DBPATH() {
        return APPFOLDER() + File.separatorChar + DB_NAME;
    }

}
