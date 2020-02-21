package com.yc.C71S3Tzggmall.bean;

import java.sql.Timestamp;
import java.util.List;

public class Orderitem {
    private Integer oiid;

    private String oid;

    private Double totalprice;

    private String status;

    private Integer uid;

    private Integer cid;

    private Integer count;

    private Timestamp time;
    
    private List<Cloth> list;
    
    public Integer getOiid() {
        return oiid;
    }

    public void setOiid(Integer oiid) {
        this.oiid = oiid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public List<Cloth> getList() {
		return list;
	}

	public void setList(List<Cloth> list) {
		this.list = list;
	}
    
    
}