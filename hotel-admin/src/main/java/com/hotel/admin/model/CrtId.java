package com.hotel.admin.model;

/**
 * ---------------------------
 * 自增序列表 (CrtId)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-05 14:24:29
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class CrtId {

	/** 当前可用编号 */
	private String crtNo;
	/** 编号类型 */
	private String crtType;

	/** 当前可用编号 */
	private String type;
	/** 编号类型 */
	private String typeno;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeno() {
		return typeno;
	}

	public void setTypeno(String typeno) {
		this.typeno = typeno;
	}

	public String getCrtNo() {
		return crtNo;
	}

	public void setCrtNo(String crtNo) {
		this.crtNo = crtNo;
	}

	public String getCrtType() {
		return crtType;
	}

	public void setCrtType(String crtType) {
		this.crtType = crtType;
	}

}