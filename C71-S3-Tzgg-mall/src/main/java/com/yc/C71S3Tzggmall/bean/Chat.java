package com.yc.C71S3Tzggmall.bean;

import java.sql.Timestamp;

public class Chat {
    private Integer chatid;

    private String cfrom;

    private String cto;

    private String chatcontent;

    private Timestamp time;

    private String status;

    public Integer getChatid() {
        return chatid;
    }

    public void setChatid(Integer chatid) {
        this.chatid = chatid;
    }

    public String getCfrom() {
        return cfrom;
    }

    public void setCfrom(String cfrom) {
        this.cfrom = cfrom == null ? null : cfrom.trim();
    }

    public String getCto() {
        return cto;
    }

    public void setCto(String cto) {
        this.cto = cto == null ? null : cto.trim();
    }

    public String getChatcontent() {
        return chatcontent;
    }

    public void setChatcontent(String chatcontent) {
        this.chatcontent = chatcontent == null ? null : chatcontent.trim();
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}