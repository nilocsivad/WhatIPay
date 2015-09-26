package com.iam_vip.biz.entity;

/**
 * Created by niloc on 2015/9/23.
 */
public class EntityTitle extends EntityBase {

    /**
     *
     */
    public EntityTitle() {
    }

    private String titleID;
    private String title;
    private Integer count = 0; // ** 使用量

    public Integer getCount() {
        return count;
    }

    public void setCount( Integer count ) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getTitleID() {
        return titleID;
    }

    public void setTitleID( String titleID ) {
        this.titleID = titleID;
    }

    public EntityTitle( String title ) {
        this.titleID = "ID" + System.currentTimeMillis();
        this.title = title;
    }
}
