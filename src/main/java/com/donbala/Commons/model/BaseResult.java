package com.donbala.Commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"limit","offset","pageStart","pageEnd"})
public class BaseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2449442727504847559L;
	private String msg;   //异步操作时，返回给页面的信息
	private int limit;  //需要展示的条数--由前端的表格传来的
	private int offset; //跳过的条数--由前端的表格传来的
	private int pageStart;//分页的开始条数
	private int pageEnd;  //分页的结束条数
	private String token;//接受前端传过来的token值
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getPageStart() {
		pageStart = offset;
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageEnd() {
		pageEnd = offset+limit;
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
