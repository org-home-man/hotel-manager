package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-05-06
 */
public class BizPuchsExtDto extends BizPuchs{

    /** 酒店编码 */
    private String hotelCode;
    /** 房间类型 */
    private String roomType;
    /** 房间样式 */
    private String roomStyle;

    /** 房间类型key */
    private String roomTypeKey;

    /** 床铺类型key */
    private String bedTypeKey;
    /** 床铺类型 */
    private String bedType;
    /** 餐食条件 */
    private String breakType;
    private String breakTypeKey;
    /** 客房面积 */
    private Double roomArea;
    /** 中文文字介绍 */
    private String introC;
    /** 英文文字介绍 */
    private String introE;
    /** 宣传照片 */
    private String photo;
    /** 默认库存数 */
    private Integer roomStock;
    /** 是否本期推荐 */
    private String recommended;

    private int scheduledays;
    private double favorableprice;
    private int evenlive;
    private String present;

    private String hotelType;
    private String hotelTypeKey;

    /** 都道府县 */
    private String provinceCode;
    /** 区市町村 */
    private String cityCode;

    /** 都道府县 */
    private String provinceCodeKey;
    private String provinceEname;
    private String provinceCname;
    /** 区市町村 */
    private String cityCodeKey;
    private String cityEname;
    private String cityCname;
    /** 酒店星级 */
    private String hotelLevel;
    /** 酒店中文名称 */
    private String hotelCname;

    /** 酒店英文名称 */

    private String hotelEname;
    /** 库存数量 */
    private Integer inventory;

    private String autoClose;

    /** 销售房价 */
    private Double sRoomPrice;
    /** 销售房价 */
    private Double sPrice;

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

    /** 结算价格  */
    private Double tPrice;
    /** 酒店地址 */
    private String hotelAddr;
    /** 酒店电话 */
    private String hotelPhone;
    /** 酒店网址 */
    private String hotelWeb;

    public int getScheduledays() {
        return scheduledays;
    }

    public void setScheduledays(int scheduledays) {
        this.scheduledays = scheduledays;
    }

    public double getFavorableprice() {
        return favorableprice;
    }

    public void setFavorableprice(double favorableprice) {
        this.favorableprice = favorableprice;
    }

    public int getEvenlive() {
        return evenlive;
    }

    public void setEvenlive(int evenlive) {
        this.evenlive = evenlive;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(String hotelLevel) {
        this.hotelLevel = hotelLevel;
    }

    public String getAutoClose() {
        return autoClose;
    }

    public void setAutoClose(String autoClose) {
        this.autoClose = autoClose;
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

    public Double getTPrice() {
        return tPrice;
    }

    public void setTPrice(Double tPrice) {
        this.tPrice = tPrice;
    }

    public String getRoomTypeKey() {
        return roomTypeKey;
    }

    public void setRoomTypeKey(String roomTypeKey) {
        this.roomTypeKey = roomTypeKey;
    }

    public void sethotelAddr(String hotelAddr) {
        this.hotelAddr = hotelAddr;
    }

    public String gethotelAddr() {
        return hotelAddr;
    }
    public void sethotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String gethotelPhone() {
        return hotelPhone;
    }
    public void sethotelWeb(String hotelWeb) {
        this.hotelWeb = hotelWeb;
    }

    public String gethotelWeb() {
        return hotelWeb;
    }

    public String getBedTypeKey() {
        return bedTypeKey;
    }

    public void setBedTypeKey(String bedTypeKey) {
        this.bedTypeKey = bedTypeKey;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }
    public String getHotelTypeKey() {
        return hotelTypeKey;
    }

    public void setHotelTypeKey(String hotelTypeKey) {
        this.hotelTypeKey = hotelTypeKey;
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

    public String getProvinceEname() {
        return provinceEname;
    }

    public void setProvinceEname(String provinceEname) {
        this.provinceEname = provinceEname;
    }

    public String getProvinceCname() {
        return provinceCname;
    }

    public void setProvinceCname(String provinceCname) {
        this.provinceCname = provinceCname;
    }

    public String getCityEname() {
        return cityEname;
    }

    public void setCityEname(String cityEname) {
        this.cityEname = cityEname;
    }

    public String getCityCname() {
        return cityCname;
    }

    public void setCityCname(String cityCname) {
        this.cityCname = cityCname;
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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Double getsRoomPrice() {
        return sRoomPrice;
    }

    public void setsRoomPrice(Double sRoomPrice) {
        this.sRoomPrice = sRoomPrice;
    }

    public Double getsPrice() {
        return sPrice;
    }

    public void setsPrice(Double sPrice) {
        this.sPrice = sPrice;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
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

    public String getBreakTypeKey() {
        return breakTypeKey;
    }

    public void setBreakTypeKey(String breakTypeKey) {
        this.breakTypeKey = breakTypeKey;
    }


    public Double getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(Double roomArea) {
        this.roomArea = roomArea;
    }

    public String getIntroC() {
        return introC;
    }

    public void setIntroC(String introC) {
        this.introC = introC;
    }

    public String getIntroE() {
        return introE;
    }

    public void setIntroE(String introE) {
        this.introE = introE;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getRoomStock() {
        return roomStock;
    }

    public void setRoomStock(Integer roomStock) {
        this.roomStock = roomStock;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

}
