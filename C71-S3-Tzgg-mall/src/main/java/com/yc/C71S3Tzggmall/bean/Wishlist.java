package com.yc.C71S3Tzggmall.bean;

import java.sql.Timestamp;

public class Wishlist extends WishlistKey {
    private Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}