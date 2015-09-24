package com.iam_vip.db;

import android.database.sqlite.SQLiteDatabase;

import com.iam_vip.util.AppHelper;

/**
 * Created by niloc on 2015/9/20.
 */
public final class SDCardSQLiteDatabase {

    private SQLiteDatabase database;

    private SDCardSQLiteDatabase() {
        database = SQLiteDatabase.openOrCreateDatabase( AppHelper.DBPATH(), null );
    }

    private static final class SDCardSQLiteDatabaseSingleton {
        private static final SDCardSQLiteDatabase DATABASE = new SDCardSQLiteDatabase();
    }

    public static SDCardSQLiteDatabase getInstance() {
        return SDCardSQLiteDatabaseSingleton.DATABASE;
    }

    public SQLiteDatabase DB() {
        return database;
    }

}
