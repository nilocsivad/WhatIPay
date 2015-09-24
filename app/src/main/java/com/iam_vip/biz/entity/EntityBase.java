/**
 *
 */
package com.iam_vip.biz.entity;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.util.Log;

import com.iam_vip.c.C;

/**
 * @author niloc
 */
public class EntityBase extends HashMap<String, Object> implements C {

    /**
     *
     */
    public EntityBase() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param capacity
     */
    public EntityBase( int capacity ) {
        super( capacity );
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * @see java.util.HashMap#get(java.lang.Object)
     */
    @Override
    public Object get( Object okey ) {
        String key = okey.toString();
        key = key.substring( 0, 1 ).toUpperCase() + key.substring( 1 );
        String result = "";
        try {
            result = this.getClass().getDeclaredMethod( "get" + key ).invoke( this ).toString();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return result;
    }

}
