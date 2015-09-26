package com.iam_vip.db.i;

import com.iam_vip.c.C;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by niloc on 2015/9/20.
 */
public interface ITableInterface<T> extends C {

    void reconstructTable();

    boolean isUpdate();

    int getVersion();

    /**
     * get current operating table name
     * @return table name
     */
    String getTableName();

    /**
     * get table define sql sentence
     */
    String getTableDefines();

    Set<String> getColumns();

    Map<String, Class> KeyValPair();

    String sql2Count();

    long countAll();

    long count( String where );

    String sql2Select();

    List<T> selectAll();

    List<T> select( String where );

    String sql2Insert();

    Object insert( T t );

    Objects insert( Map<String, Object> param );

    String sql2Delete();

    int deleteAll();

    int delete( T t );

    int delete( String where );

    int delete( Map<String, Object> param );

    String sql2Modify();

    int modify( T t );

    int modify( Map<String, Object> param );

}
