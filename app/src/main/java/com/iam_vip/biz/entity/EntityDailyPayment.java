/**
 *
 */
package com.iam_vip.biz.entity;

import com.iam_vip.util.DTUtil;

import java.util.Random;

/**
 * @author niloc
 */
public class EntityDailyPayment extends EntityBase {

    /**
     *
     */
    public EntityDailyPayment() {
    }

    private Integer typeVal; // ** 101 102 103 104
    private String type; // ** 收入 支出 借入 借出
    private String title;
    private String time;
    private Double money;
    private String details;

    public static EntityDailyPayment newInstance() {
        EntityDailyPayment entity = new EntityDailyPayment();
        entity.typeVal = new Random().nextInt( 3 ) + 101;
        entity.title = "title-" + Math.random();
        entity.time = DTUtil.nowDT();
        entity.money = Math.random();
        entity.details = DTUtil.nowDT() + "-" + Math.random();
        return entity;
    }

    /**
     * @return the type
     */
    public final String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public final void setType( String type ) {
        this.type = type;
    }

    /**
     * @return the title
     */
    public final String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public final void setTitle( String title ) {
        this.title = title;
    }

    /**
     * @return the time
     */
    public final String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public final void setTime( String time ) {
        this.time = time;
    }

    /**
     * @return the money
     */
    public final Double getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public final void setMoney( Double money ) {
        this.money = money;
    }

    /**
     * @return the details
     */
    public final String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public final void setDetails( String details ) {
        this.details = details;
    }

    /**
     * @return the typeVal
     */
    public final Integer getTypeVal() {
        return typeVal;
    }

    /**
     * @param typeVal the typeVal to set
     */
    public final void setTypeVal( Integer typeVal ) {
        this.typeVal = typeVal;
    }
}
