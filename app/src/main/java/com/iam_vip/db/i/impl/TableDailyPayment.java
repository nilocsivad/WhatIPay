package com.iam_vip.db.i.impl;

import android.database.Cursor;

import com.iam_vip.biz.entity.EntityDailyPayment;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by niloc on 2015/9/20.
 */
public class TableDailyPayment extends DefaultTableImplement< EntityDailyPayment > {

    /** 当前发布是否要更新数据库表结构 **/
    private static final boolean UPDATE = false;
    /** 当前数据表结构版本号 **/
    private static final int VERSION = 1;

    public static final String TABLE_NAME = "tbDailyPayment";
    private static final Map< String, Class > COL_TYPE_PAIR = new LinkedHashMap<>();

    static {
        COL_TYPE_PAIR.put( "typeVal", Integer.class );
        COL_TYPE_PAIR.put( "title", String.class );
        COL_TYPE_PAIR.put( "time", String.class );
        COL_TYPE_PAIR.put( "money", Double.class );
        COL_TYPE_PAIR.put( "details", String.class );
    }

    @Override
    public boolean isUpdate() {
        return UPDATE;
    }

    @Override
    public int getVersion() {
        return VERSION;
    }

    /**
     * get current operating table name
     * @return table name
     */
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Set< String > getColumns() {
        return COL_TYPE_PAIR.keySet();
    }

    @Override
    public Map< String, Class > KeyValPair() {
        return COL_TYPE_PAIR;
    }

    /**
     * get table define sql sentence
     */
    @Override
    public String getTableDefines() {
        return "CREATE TABLE " + TABLE_NAME +
                "(" +
                "	typeVal INTEGER not null," +
                "	title TEXT not null," +
                "	time CHAR(19)," +
                "	money REAL," +
                "	details TEXT," +
                "	primary key (time)" +
                ")";
    }

    @Override
    public String sql2Insert() {
        return "INSERT INTO " + TABLE_NAME +
                "(" +
                "	typeVal," +
                "	title," +
                "	time," +
                "	money," +
                "	details" +
                ") " +
                "VALUES" +
                "(" +
                "	?," +
                "	?," +
                "	?," +
                "	?," +
                "	?" +
                ")";
    }

    @Override
    public Object insert( EntityDailyPayment entityDailyPayment ) {
        db.execSQL( this.sql2Insert(), this.getVals( entityDailyPayment ) );
        return 1;
    }

    @Override
    public List< EntityDailyPayment > select( String where ) {
        List< EntityDailyPayment > list = new ArrayList< EntityDailyPayment >( SIZE );
        Cursor cursor = db.rawQuery( this.sql2Select() + where, null );
        while ( cursor.moveToNext() ) {
            EntityDailyPayment entity = new EntityDailyPayment();
            Object o = this.setFieldsVal( entity, cursor );
            if ( o != null ) list.add( ( EntityDailyPayment ) o );
        }
        return list;
    }

    @Override
    public Objects insert( Map param ) {
        return null;
    }

    @Override
    public int delete( EntityDailyPayment o ) {
        return 0;
    }

    @Override
    public int delete( Map param ) {
        return 0;
    }

    @Override
    public String sql2Modify() {
        return null;
    }

    @Override
    public int modify( EntityDailyPayment o ) {
        return 0;
    }

    @Override
    public int modify( Map param ) {
        return 0;
    }
}
