package com.yc.C71S3Tzggmall.vo;

public class Result {
	
	//返回的结果码 正确1 错误0 
	private int code;
	
	//返回的消息
	private String msg;
	
	//返回的数据
	private Object Data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}

	public Result(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		Data = data;
	}
	
	public Result(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public Result(int code) {
		super();
		this.code = code;
		
	}
	

}
