package com.yc.C71S3Tzggmall.bean;

import java.sql.Timestamp;
import java.util.List;

public class Cloth {
    private Integer cid;

    private String name;

    private Double shopprice;

    private Double realprice;

    private String image;

    private Integer tid;

    private Integer typeid;

    private Integer restcount;

    private Integer solecount;

    private Timestamp arrivetime;

    private String detail;
    
    private Integer count;
    
    private List<Cloth> list;
    
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getShopprice() {
        return shopprice;
    }

    public void setShopprice(Double shopprice) {
        this.shopprice = shopprice;
    }

    public Double getRealprice() {
        return realprice;
    }

    public void setRealprice(Double realprice) {
        this.realprice = realprice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getRestcount() {
        return restcount;
    }

    public void setRestcount(Integer restcount) {
        this.restcount = restcount;
    }

    public Integer getSolecount() {
        return solecount;
    }

    public void setSolecount(Integer solecount) {
        this.solecount = solecount;
    }

    public Timestamp getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(Timestamp arrivetime) {
        this.arrivetime = arrivetime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Cloth> getList() {
		return list;
	}

	public void setList(List<Cloth> list) {
		this.list = list;
	}
    
    
}