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

    private Integer titleID;
    private String title;
    private Integer count; // ** 使用量

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

    public Integer getTitleID() {
        return titleID;
    }

    public void setTitleID( Integer titleID ) {
        this.titleID = titleID;
    }
}
