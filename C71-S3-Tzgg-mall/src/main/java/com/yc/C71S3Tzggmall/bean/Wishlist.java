package com.yc.C71S3Tzggmall.bean;

import java.sql.Timestamp;

public class Wishlist extends WishlistKey {
    private Integer uid;

    private Timestamp time;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}