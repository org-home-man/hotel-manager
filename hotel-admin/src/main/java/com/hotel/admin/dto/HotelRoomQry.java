package com.hotel.admin.dto;

/**
 * 酒店客房检索表
 */
public class HotelRoomQry {
	/** 酒店星级 */
	private  String hotelLevel;
	/** 房间类型 */
	private String roomType;
	/** 房间样式 */
	private String roomStyle;
	/** 床铺类型 */
	private String bedType;
	/** 餐食条件 */
	private String breakType;
	/** 客房面积 */
	private Double roomArea;
	/** 默认库存数 */
	private Integer roomStock;
	/** 退房时间 */
	private String outDateEnd;
	/** 入住时间 */
	private String inDateStart;
	/** 都道府县 */
	private String provinceCode;
	/** 区市町村 */
	private String cityCode;
	/** 都道府县 */
	private String provinceCodeKey;
	/** 区市町村 */
	private String cityCodeKey;
	/** 房间价格 */
	private Integer highRoomPrice;
	/** 房间价格 */
	private Integer lowRoomPrice;
	/** 酒店名称 */
	private String hotelName;
	/** 酒店类型 */
	private String hotelType;


	/** 免费WIFY */
	private String iswify;
	/** 24H front */
	private String isfront;
	/** 无障碍通道 */
	private String isbarrifr;
	/** 阳台/露台 */
	private String isbalcony;
	/** 厨房 */
	private String iskitchen;
	/** 窗户 */
	private String iswindow;
	/** 空调 */
	private String isheat;
	/** 冰箱 */
	private String isicebox;
	/** 熨衣设备 */
	private String isiron;
	/** 禁烟房 */
	private String isnosmk;
	/** 景观房 */
	private String islandscape;
	/** 高楼层 */
	private String ishighrise;
	/** 停车场 */
	private String ispark;
	/** 健身房 */
	private String isgym;
	/** 游泳池 */
	private String isswmp;
	/** 海滩 */
	private String isbeach;
	/** 温泉 */
	private String ishotsp;
	/** 儿童中心 */
	private String ischildct;
	/** 客房服务 */
	private String isroomserv;
	/** 按摩 */
	private String isknead;
	/** 行政酒廊 */
	private String islounge;
	/** 附近有24H便利店 */
	private String issuper;
	/** 附近有公交车 */
	private String isbus;
	/** 附近有轨道交通站 */
	private String istrafic;
	/** 附近有餐厅 */
	private String isrestau;

	public String getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(String hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomStyle() {
		return roomStyle;
	}

	public void setRoomStyle(String roomStyle) {
		this.roomStyle = roomStyle;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public String getBreakType() {
		return breakType;
	}

	public void setBreakType(String breakType) {
		this.breakType = breakType;
	}

	public Double getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(Double roomArea) {
		this.roomArea = roomArea;
	}

	public Integer getRoomStock() {
		return roomStock;
	}

	public void setRoomStock(Integer roomStock) {
		this.roomStock = roomStock;
	}

	public String getOutDateEnd() {
		return outDateEnd;
	}

	public void setOutDateEnd(String outDateEnd) {
		this.outDateEnd = outDateEnd;
	}

	public String getInDateStart() {
		return inDateStart;
	}

	public void setInDateStart(String inDateStart) {
		this.inDateStart = inDateStart;
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

	public Integer getHighRoomPrice() {
		return highRoomPrice;
	}

	public void setHighRoomPrice(Integer highRoomPrice) {
		this.highRoomPrice = highRoomPrice;
	}

	public Integer getLowRoomPrice() {
		return lowRoomPrice;
	}

	public void setLowRoomPrice(Integer lowRoomPrice) {
		this.lowRoomPrice = lowRoomPrice;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getIswify() {
		return iswify;
	}

	public void setIswify(String iswify) {
		this.iswify = iswify;
	}

	public String getIsfront() {
		return isfront;
	}

	public void setIsfront(String isfront) {
		this.isfront = isfront;
	}

	public String getIsbarrifr() {
		return isbarrifr;
	}

	public void setIsbarrifr(String isbarrifr) {
		this.isbarrifr = isbarrifr;
	}

	public String getIsbalcony() {
		return isbalcony;
	}

	public void setIsbalcony(String isbalcony) {
		this.isbalcony = isbalcony;
	}

	public String getIskitchen() {
		return iskitchen;
	}

	public void setIskitchen(String iskitchen) {
		this.iskitchen = iskitchen;
	}

	public String getIswindow() {
		return iswindow;
	}

	public void setIswindow(String iswindow) {
		this.iswindow = iswindow;
	}

	public String getIsheat() {
		return isheat;
	}

	public void setIsheat(String isheat) {
		this.isheat = isheat;
	}

	public String getIsicebox() {
		return isicebox;
	}

	public void setIsicebox(String isicebox) {
		this.isicebox = isicebox;
	}

	public String getIsiron() {
		return isiron;
	}

	public void setIsiron(String isiron) {
		this.isiron = isiron;
	}

	public String getIsnosmk() {
		return isnosmk;
	}

	public void setIsnosmk(String isnosmk) {
		this.isnosmk = isnosmk;
	}

	public String getIslandscape() {
		return islandscape;
	}

	public void setIslandscape(String islandscape) {
		this.islandscape = islandscape;
	}

	public String getIshighrise() {
		return ishighrise;
	}

	public void setIshighrise(String ishighrise) {
		this.ishighrise = ishighrise;
	}

	public String getIspark() {
		return ispark;
	}

	public void setIspark(String ispark) {
		this.ispark = ispark;
	}

	public String getIsgym() {
		return isgym;
	}

	public void setIsgym(String isgym) {
		this.isgym = isgym;
	}

	public String getIsswmp() {
		return isswmp;
	}

	public void setIsswmp(String isswmp) {
		this.isswmp = isswmp;
	}

	public String getIsbeach() {
		return isbeach;
	}

	public void setIsbeach(String isbeach) {
		this.isbeach = isbeach;
	}

	public String getIshotsp() {
		return ishotsp;
	}

	public void setIshotsp(String ishotsp) {
		this.ishotsp = ishotsp;
	}

	public String getIschildct() {
		return ischildct;
	}

	public void setIschildct(String ischildct) {
		this.ischildct = ischildct;
	}

	public String getIsroomserv() {
		return isroomserv;
	}

	public void setIsroomserv(String isroomserv) {
		this.isroomserv = isroomserv;
	}

	public String getIsknead() {
		return isknead;
	}

	public void setIsknead(String isknead) {
		this.isknead = isknead;
	}

	public String getIslounge() {
		return islounge;
	}

	public void setIslounge(String islounge) {
		this.islounge = islounge;
	}

	public String getIssuper() {
		return issuper;
	}

	public void setIssuper(String issuper) {
		this.issuper = issuper;
	}

	public String getIsbus() {
		return isbus;
	}

	public void setIsbus(String isbus) {
		this.isbus = isbus;
	}

	public String getIstrafic() {
		return istrafic;
	}

	public void setIstrafic(String istrafic) {
		this.istrafic = istrafic;
	}

	public String getIsrestau() {
		return isrestau;
	}

	public void setIsrestau(String isrestau) {
		this.isrestau = isrestau;
	}

	public String getHotelType() {
		return hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
}