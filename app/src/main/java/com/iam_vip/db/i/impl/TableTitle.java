package com.iam_vip.db.i.impl;

import android.database.Cursor;

import com.iam_vip.biz.entity.EntityTitle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by niloc on 2015/9/23.
 */
public class TableTitle extends DefaultTableImplement<EntityTitle> {

    public static final String TABLE_NAME = "tbTitle";
    private static final Map<String, Class> COL_TYPE_PAIR = new LinkedHashMap<>();

    static {
        COL_TYPE_PAIR.put( "titleID", Integer.class );
        COL_TYPE_PAIR.put( "title", String.class );
        COL_TYPE_PAIR.put( "count", Integer.class );
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
    public Set<String> getColumns() {
        return COL_TYPE_PAIR.keySet();
    }

    @Override
    public Map<String, Class> KeyValPair() {
        return COL_TYPE_PAIR;
    }

    /**
     * get table define sql sentence
     */
    @Override
    public String getTableDefines() {
        return "CREATE TABLE " + TABLE_NAME +
                "(" +
                "	titleID INTEGER not null," +
                "	title TEXT not null," +
                "	count INTEGER," +
                "	primary key (titleID)" +
                ")";
    }

    @Override
    public String sql2Insert() {
        return "INSERT INTO " + TABLE_NAME +
                "(" +
                "	titleID," +
                "	title," +
                "	count" +
                ") " +
                "VALUES" +
                "(" +
                "	?," +
                "	?," +
                "	?" +
                ")";
    }

    @Override
    public Object insert( EntityTitle entityTitle ) {
        db.execSQL( this.sql2Insert(), this.getVals( entityTitle ) );
        return 1;
    }

    @Override
    public List<EntityTitle> select( String where ) {
        List<EntityTitle> list = new ArrayList<EntityTitle>( SIZE );
        Cursor cursor = db.rawQuery( this.sql2Select() + where, null );
        while ( cursor.moveToNext() ) {
            EntityTitle entity = new EntityTitle();
            Object o = this.setFieldsVal( entity, cursor );
            if ( o != null )
                list.add( (EntityTitle) o );
        }
        return list;
    }

    @Override
    public Objects insert( Map param ) {
        return null;
    }

    @Override
    public int delete( EntityTitle o ) {
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
    public int modify( EntityTitle o ) {
        return 0;
    }

    @Override
    public int modify( Map param ) {
        return 0;
    }
}
