package com.hotel.admin.dto;

import com.hotel.common.bean.ExcelColumn;
import com.hotel.common.entity.Entity;

public class UserRequestReportDto extends Entity{

    /** 都道府县 */
    @ExcelColumn(value = "督道府县",col = 1)
    private String provinceCode;
    /** 区市町村 */
    @ExcelColumn(value = "区市町村",col = 2)
    private String cityCode;
    /** 酒店编码 */
    @ExcelColumn(value = "酒店编号",col = 3)
    private String hotelCode;
    /** 酒店中文名称 */
    @ExcelColumn(value = "酒店名称",col = 4)
    private String hotelName;

    @ExcelColumn(value = "酒店类型",col = 5)
    private String hotelType;

    /** 酒店星级 */
    @ExcelColumn(value = "酒店星级",col = 6)
    private String hotelLevel;
    /** 房间类型 */
    @ExcelColumn(value = "房间类型",col = 7)
    private String roomType;
    /** 房间样式 */
    @ExcelColumn(value = "房间样式",col = 8)
    private String roomStyle;

    /** 床铺类型 */
    @ExcelColumn(value = "床铺类型",col = 9)
    private String bedType;
    /** 餐食条件 */
    @ExcelColumn(value = "餐食条件",col = 10)
    private String breakType;
    /** 客房面积 */
    @ExcelColumn(value = "客房面积",col = 11)
    private Double roomArea;

    /** 酒店地址 */
    @ExcelColumn(value = "酒店地址",col = 12)
    private String hotelAddr;
    /** 酒店电话 */
    @ExcelColumn(value = "酒店电话",col = 13)
    private String hotelPhone;
    /** 酒店网址 */
    @ExcelColumn(value = "酒店网址",col = 14)
    private String hotelWeb;

    /** 销售房价 */
    @ExcelColumn(value = "销售房价",col = 15)
    private Double totalSAmount;

    @ExcelColumn(value="酒店常用信息",col = 16)
    private String hotelBasicInfo;

    @ExcelColumn(value = "入住日期",col=17)
    private String inDateStart;

    @ExcelColumn(value="退房日期",col = 18)
    private String outDateEnd;

    @ExcelColumn(value="代表姓名",col = 19)
    private String pName;

    @ExcelColumn(value="护照号",col = 20)
    private String passport;

    @ExcelColumn(value="出生日期",col = 21)
    private String birth;

    @ExcelColumn(value="手机号",col = 22)
    private String phone;

    @ExcelColumn(value="email地址",col = 23)
    private String emailAddress;

    @ExcelColumn(value="成年人数",col = 24)
    private String adultNum;

    @ExcelColumn(value="儿童数",col = 25)
    private String childNum;

    @ExcelColumn(value="预定房间数",col = 26)
    private String roomNum;

    /** 6-12岁儿童 */
    @ExcelColumn(value="6-12岁儿童",col = 27)
    private String children612;
    /** 4-6岁儿童 */
    @ExcelColumn(value="4-6岁儿童",col = 28)
    private String children46;
    /** 4岁以下儿童 */
    @ExcelColumn(value="4岁以下儿童",col = 29)
    private String children4;

    @ExcelColumn(value="订单创建人",col = 30)
    private String createName;
    @ExcelColumn(value="订单创建时间",col = 31)
    private String createTime;
    @ExcelColumn(value="订单更新人",col = 32)
    private String updateName;
    @ExcelColumn(value="订单更新时间",col = 33)
    private String updateTime;



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

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getHotelWeb() {
        return hotelWeb;
    }

    public void setHotelWeb(String hotelWeb) {
        this.hotelWeb = hotelWeb;
    }

    public Double getTotalSAmount() {
        return totalSAmount;
    }

    public void setTotalSAmount(Double totalSAmount) {
        this.totalSAmount = totalSAmount;
    }

    public String getHotelBasicInfo() {
        return hotelBasicInfo;
    }

    public void setHotelBasicInfo(String hotelBasicInfo) {
        this.hotelBasicInfo = hotelBasicInfo;
    }

    public String getInDateStart() {
        return inDateStart;
    }

    public void setInDateStart(String inDateStart) {
        this.inDateStart = inDateStart;
    }

    public String getOutDateEnd() {
        return outDateEnd;
    }

    public void setOutDateEnd(String outDateEnd) {
        this.outDateEnd = outDateEnd;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAdultNum() {
        return adultNum;
    }

    public void setAdultNum(String adultNum) {
        this.adultNum = adultNum;
    }

    public String getChildNum() {
        return childNum;
    }

    public void setChildNum(String childNum) {
        this.childNum = childNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getChildren612() {
        return children612;
    }

    public void setChildren612(String children612) {
        this.children612 = children612;
    }

    public String getChildren46() {
        return children46;
    }

    public void setChildren46(String children46) {
        this.children46 = children46;
    }

    public String getChildren4() {
        return children4;
    }

    public void setChildren4(String children4) {
        this.children4 = children4;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
