package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

import javax.persistence.Transient;


/**
 * ---------------------------
 * 酒店信息表 (BizHotl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-30 17:15:22
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizHotl extends BusinessEntity{
//    private int id;
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

	/** 都道府县 */
	private String provinceCname;
	/** 区市町村 */
	private String cityCname;
	/** 都道府县 */
	private String provinceEname;
	/** 区市町村 */
	private String cityEname;

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

	private Byte  delFlag;

//	public Byte getdelFlag() {
//	return delFlag;
//}
//
//	public void setdelFlag(Byte delFlag) {
//		this.delFlag = delFlag;
//	}

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
	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}
	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}
	public void setHotelFax(String hotelFax) {
		this.hotelFax= hotelFax;
	}
	public void setHotelWeb(String hotelWeb ) {
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

	public String getProvinceCname() {
		return provinceCname;
	}
	public void setProvinceCname(String provinceCname) {
		this.provinceCname = provinceCname;
	}
	public String getProvinceEname() {
		return provinceEname;
	}
	public void setProvinceEname(String provinceEname) {
		this.provinceEname = provinceEname;
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

	public String getcityEname() {
		return cityEname;
	}
	public void setcityEname(String cityEname) {
		this.cityEname = cityEname;
	}
	public String getcityCname() {
		return cityCname;
	}
	public void setcityCname(String cityCname) {
		this.cityCname = cityCname;
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

}