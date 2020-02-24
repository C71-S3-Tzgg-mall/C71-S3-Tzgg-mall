package com.yc.C71S3Tzggmall.vo;

public class Bill {
	
	private Integer time;
	
	private Integer count;

	public Bill() {
		super();
	}

	public Bill(Integer time,Integer count) {
		super();
		this.time=time;
		this.count=count;
		
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
