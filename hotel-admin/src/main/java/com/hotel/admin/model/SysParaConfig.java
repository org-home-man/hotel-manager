package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

import javax.persistence.Transient;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfig)         
 * ---------------------------
 */
public class SysParaConfig extends BusinessEntity {

	/** 参数编码 */
	private String paraCode;
	/** 参数子码1 */
	private String paraSubCode1;
	/** 参数子码2 */
	private String paraSubCode2;
	/** 参数值1 */
	private String paraValue1;
	/** 参数值2 */
	private String paraValue2;
	/** 参数值3 */
	private String paraValue3;
	/** 备注信息 */
	private String remark;
	/** 参数值3 */
	@Transient
	private String language;

	public String getlanguage() {
		return language;
	}

	public void setlanguage(String language) {
		this.language = language;
	}

	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}

	public String getParaSubCode1() {
		return paraSubCode1;
	}

	public void setParaSubCode1(String paraSubCode1) {
		this.paraSubCode1 = paraSubCode1;
	}

	public String getParaSubCode2() {
		return paraSubCode2;
	}

	public void setParaSubCode2(String paraSubCode2) {
		this.paraSubCode2 = paraSubCode2;
	}

	public String getParaValue1() {
		return paraValue1;
	}

	public void setParaValue1(String paraValue1) {
		this.paraValue1 = paraValue1;
	}

	public String getParaValue2() {
		return paraValue2;
	}

	public void setParaValue2(String paraValue2) {
		this.paraValue2 = paraValue2;
	}

	public String getParaValue3() {
		return paraValue3;
	}

	public void setParaValue3(String paraValue3) {
		this.paraValue3 = paraValue3;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}