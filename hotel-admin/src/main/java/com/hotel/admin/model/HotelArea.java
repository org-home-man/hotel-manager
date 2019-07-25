package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

/**
 * ---------------------------
 * 地区码表 (HotelArea)         
 * ---------------------------
 */
public class HotelArea extends BusinessEntity{

	/** 地区级别 */
	private String areaLvl;
	/** 地区码 */
	private String areaCode;
	/** 地区码key */
	private String codeKey;
	/** 地区中文名称 */
	private String areaCname;
	/** 地区英文名称 */
	private String areaEname;
	/** 备注信息 */
	private String remark;

	public String getAreaLvl() {
		return areaLvl;
	}

	public void setAreaLvl(String areaLvl) {
		this.areaLvl = areaLvl;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getAreaCname() {
		return areaCname;
	}

	public void setAreaCname(String areaCname) {
		this.areaCname = areaCname;
	}

	public String getAreaEname() {
		return areaEname;
	}

	public void setAreaEname(String areaEname) {
		this.areaEname = areaEname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}