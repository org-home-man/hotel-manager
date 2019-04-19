package com.hotel.admin.model;

/**
 * ---------------------------
 * 酒店信息表 (BizHotl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-30 17:15:22
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizHotl extends BaseModel{

	/** 酒店编号 */
	private String hotelCode;
	/** 国家编码 */
	private String countryCode;
	/** 都道府县 */
	private String provinceCode;
	/** 区市町村 */
	private String cityCode;

	/** 都道府县 */
	private String provinceCodeKey;
	/** 区市町村 */
	private String cityCodeKey;

	/** 酒店类型 */
	private String hotelType;
	/** 酒店星级 */
	private String hotelLevel;
	/** 酒店类型 */
	private String hotelTypeKey;
	/** 酒店星级 */
	private String hotelLevelKey;
	/** 酒店中文名称 */
	private String hotelCname;
	/** 酒店英文名称 */
	private String hotelEname;
	/** 酒店地址 */
	private String hotelAddr;
	/** 酒店电话 */
	private String hotelPhone;
	/** 酒店传真 */
	private String hotelFax;
	/** 酒店网址 */
	private String hotelWeb;
	/** 创建人员 */
	private String creatCy;
	/** 创建时间 */
	private java.util.Date creatTime;
	/** 更新人员 */
	private String lastUpdateBy;
	/** 更新时间 */
	private java.util.Date lastUpdateTime;

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getCountryCode() {
		return countryCode;
	}


	public String getHotelAddr() {
		return hotelAddr;
	}
	public String getHotelPhone() {
		return hotelPhone;
	}
	public String getHotelFax() {
		return hotelFax;
	}
	public String getHotelWeb() {
		return hotelWeb;
	}
	public void setHotelAddr() {
		this.hotelAddr = hotelAddr;
	}
	public void setHotelPhone() {
		this.hotelPhone = hotelPhone;
	}
	public void setHotelFax() {
		this.hotelFax= hotelFax;
	}
	public void setHotelWeb() {
		this.hotelWeb = hotelWeb;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}
	public String getProvinceCodeKey() {
		return provinceCodeKey;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public void setProvinceCodeKey(String provinceCodeKey) {
		this.provinceCodeKey = provinceCodeKey;
	}

	public String getCityCode() {
		return cityCode;
	}
	public String getCityCodeKey() {
		return cityCodeKey;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public void setCityCodeKey(String cityCodeKey) {
		this.cityCodeKey = cityCodeKey;
	}


	public String getHotelType() {
		return hotelType;
	}
	public String getHotelTypeKey() {
		return hotelTypeKey;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public void setHotelTypeKey(String hotelTypeKey) {
		this.hotelTypeKey = hotelTypeKey;
	}

	public String getHotelLevel() {
		return hotelLevel;
	}
	public String getHotelLevelKey() {
		return hotelLevelKey;
	}

	public void setHotelLevel(String hotelLevel) {
		this.hotelLevel = hotelLevel;
	}
	public void setHotelLevelKey(String hotelLevelKey) {
		this.hotelLevelKey = hotelLevelKey;
	}


	public String getHotelCname() {
		return hotelCname;
	}

	public void setHotelCname(String hotelCname) {
		this.hotelCname = hotelCname;
	}

	public String getHotelEname() {
		return hotelEname;
	}

	public void setHotelEname(String hotelEname) {
		this.hotelEname = hotelEname;
	}

	public String getCreatCy() {
		return creatCy;
	}

	public void setCreatCy(String creatCy) {
		this.creatCy = creatCy;
	}

	public java.util.Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(java.util.Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public java.util.Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(java.util.Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}