package com.belief.module.model;

import java.io.Serializable;
import java.util.Date;

public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 8465007746488004330L;

    private String id;
    private Date createdAt;
    private Date updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

}
