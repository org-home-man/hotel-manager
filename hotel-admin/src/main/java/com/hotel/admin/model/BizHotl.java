package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

import javax.persistence.Transient;


/**
 * ---------------------------
 * 酒店信息表 (BizHotl)         
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

	private Integer scheduledays;
	private Double favorableprice;
	private Integer evenlive;
	private String present;
	private String specialMatters;
	private Integer roomStock;

	private Byte  delFlag;

	public String getSpecialMatters() {
		return specialMatters;
	}

	public void setSpecialMatters(String specialMatters) {
		this.specialMatters = specialMatters;
	}

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

	public String getProvinceCodeKey() {
		return provinceCodeKey;
	}

	public void setProvinceCodeKey(String provinceCodeKey) {
		this.provinceCodeKey = provinceCodeKey;
	}

	public String getCityCodeKey() {
		return cityCodeKey;
	}

	public void setCityCodeKey(String cityCodeKey) {
		this.cityCodeKey = cityCodeKey;
	}

	public String getProvinceCname() {
		return provinceCname;
	}

	public void setProvinceCname(String provinceCname) {
		this.provinceCname = provinceCname;
	}

	public String getCityCname() {
		return cityCname;
	}

	public void setCityCname(String cityCname) {
		this.cityCname = cityCname;
	}

	public String getProvinceEname() {
		return provinceEname;
	}

	public void setProvinceEname(String provinceEname) {
		this.provinceEname = provinceEname;
	}

	public String getCityEname() {
		return cityEname;
	}

	public void setCityEname(String cityEname) {
		this.cityEname = cityEname;
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

	public String getHotelTypeKey() {
		return hotelTypeKey;
	}

	public void setHotelTypeKey(String hotelTypeKey) {
		this.hotelTypeKey = hotelTypeKey;
	}

	public String getHotelLevelKey() {
		return hotelLevelKey;
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

	public String getHotelAddr() {
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}

	public String getHotelPhone() {
		return hotelPhone;
	}

	public void setHotelPhone(String hotelPhone) {
		this.hotelPhone = hotelPhone;
	}

	public String getHotelFax() {
		return hotelFax;
	}

	public void setHotelFax(String hotelFax) {
		this.hotelFax = hotelFax;
	}

	public String getHotelWeb() {
		return hotelWeb;
	}

	public void setHotelWeb(String hotelWeb) {
		this.hotelWeb = hotelWeb;
	}

	public Integer getScheduledays() {
		return scheduledays;
	}

	public void setScheduledays(Integer scheduledays) {
		this.scheduledays = scheduledays;
	}

	public Double getFavorableprice() {
		return favorableprice;
	}

	public void setFavorableprice(Double favorableprice) {
		this.favorableprice = favorableprice;
	}

	public Integer getEvenlive() {
		return evenlive;
	}

	public void setEvenlive(Integer evenlive) {
		this.evenlive = evenlive;
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}

	public Integer getRoomStock() {
		return roomStock;
	}

	public void setRoomStock(Integer roomStock) {
		this.roomStock = roomStock;
	}

	public Byte getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}
}