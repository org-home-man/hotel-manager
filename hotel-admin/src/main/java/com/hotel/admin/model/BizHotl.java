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
	/** 酒店类型 */
	private String hotelType;
	/** 酒店星级 */
	private String hotelLevel;
	/** 酒店中文名称 */
	private String hotelCname;
	/** 酒店英文名称 */
	private String hotelEname;
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

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getHotelType() {
		return hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}

	public String getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(String hotelLevel) {
		this.hotelLevel = hotelLevel;
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