package com.iam_vip.db.i.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iam_vip.c.C;
import com.iam_vip.db.SDCardSQLiteDatabase;
import com.iam_vip.db.i.ITableInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by niloc on 2015/9/20.
 */
public abstract class DefaultTableImplement< T > implements ITableInterface< T > {

    public static final String SQL_VALID_TABLE = "SELECT COUNT(*) FROM sqlite_master WHERE TYPE = 'table' AND NAME = ?";

    public static final String SQL_COUNT_ALL = "SELECT COUNT(*) FROM ";

    public static final String SQL_LIST_ALL = "SELECT * FROM ";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS ";

    protected SQLiteDatabase db;

    /**
     *
     */
    protected DefaultTableImplement() {
        if ( db == null ) db = SDCardSQLiteDatabase.getInstance().DB();

        this.validateTable();
    }

    protected String get( String col ) {
        return "get" + col.substring( 0, 1 ).toUpperCase() + col.substring( 1 );
    }

    protected Object[] getVals( Object instance ) {
        return this.getVals( instance, 0 );
    }

    protected Object[] getVals( Object instance, int start ) {
        return this.getVals( instance, start, 0 );
    }

    protected Object[] getVals( Object instance, int start, int end ) {
        try {
            LinkedHashSet< Object > set = new LinkedHashSet<>();
            int i = 0;
            for ( String key : this.KeyValPair().keySet() ) {
                if ( i < start ) {
                } else if ( end < 0 ) {
                } else if ( end > 0 && i >= end ) {
                } else {
                    set.add( instance.getClass().getDeclaredMethod( this.get( key ) ).invoke( instance ) );
                }
                i++;
            }
            return set.toArray();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    protected String set( String col ) {
        return "set" + col.substring( 0, 1 ).toUpperCase() + col.substring( 1 );
    }

    protected void setVal( Cursor cursor, Object instance, String col, Class type ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object val = null;
        if ( type == String.class ) {
            val = cursor.getString( cursor.getColumnIndex( col ) );
        } else if ( type == Double.class ) {
            val = cursor.getDouble( cursor.getColumnIndex( col ) );
        } else if ( type == Integer.class ) {
            val = cursor.getInt( cursor.getColumnIndex( col ) );
        }
        instance.getClass().getDeclaredMethod( this.set( col ), type ).invoke( instance, val );
    }

    protected Object setFieldsVal( Object instance, Cursor cursor ) {
        try {
            for ( Map.Entry< String, Class > item : this.KeyValPair().entrySet() )
                this.setVal( cursor, instance, item.getKey(), item.getValue() );
            return instance;
        } catch ( Exception e ) {
            StackTraceElement stack = Thread.currentThread().getStackTrace()[ 1 ];
            Log.e( APP, stack.getClassName() + "." + stack.getMethodName() + " throw " + e.getMessage() );
        }
        return null;
    }

    @Override
    public void reconstructTable() {
        db.execSQL( SQL_DROP_TABLE + this.getTableName() );
        db.execSQL( this.getTableDefines() );
    }

    /**
     * check whether exists table
     */
    private void validateTable() {
        Cursor cursor = db.rawQuery( SQL_VALID_TABLE, new String[] { this.getTableName().trim() } );
        if ( cursor.moveToFirst() ) {
            long count = cursor.getLong( 0 );
            if ( count == 0 ) db.execSQL( this.getTableDefines() );
        }
    }

    @Override
    public String sql2Count() {
        return SQL_COUNT_ALL + this.getTableName();
    }

    @Override
    public long countAll() {
        return this.count( "" );
    }

    @Override
    public long count( String where ) {
        Cursor cursor = this.db.rawQuery( this.sql2Count() + where, null );
        long l = 0;
        if ( cursor.moveToFirst() ) {
            l = cursor.getLong( 0 );
        }
        return l;
    }

    @Override
    public String sql2Select() {
        return SQL_LIST_ALL + this.getTableName();
    }

    @Override
    public List< T > selectAll() {
        return this.select( "" );
    }

    @Override
    public String sql2Delete() {
        return "DELETE FROM " + this.getTableName();
    }

    @Override
    public int deleteAll() {
        return this.delete( "" );
    }

    @Override
    public int delete( String where ) {
        this.db.execSQL( this.sql2Delete() + where, new Object[] { 0 } );
        return 1;
    }

}
