package com.hotel.admin.dto;

import com.hotel.common.bean.ExcelColumn;
import com.hotel.common.entity.Entity;

public class ManagerRequestReportDto extends Entity{

    /** 酒店中文名称 */
    @ExcelColumn(value = "酒店名称",col = 1)
    private String hotelName;

    /** 酒店中文名称 */
    @ExcelColumn(value = "订单编号",col = 2)
    private String orderCode;

    /** 房间类型 */
    @ExcelColumn(value = "房间类型",col = 3)
    private String roomType;

    @ExcelColumn(value="预定房间数",col = 4)
    private String roomNum;

    @ExcelColumn(value="代表名称",col = 5)
    private String pName;

    @ExcelColumn(value="成年人数",col = 6)
    private String adultNum;

    @ExcelColumn(value="儿童数",col = 7)
    private String childNum;

    /** 6-12岁儿童 */
    @ExcelColumn(value="6-12岁儿童",col = 8)
    private String children612;
    /** 4-6岁儿童 */
    @ExcelColumn(value="4-6岁儿童",col = 9)
    private String children46;
    /** 4岁以下儿童 */
    @ExcelColumn(value="4岁以下儿童",col = 10)
    private String children4;

    @ExcelColumn(value = "入住期间",col = 11)
    private String inEnd;

    /** 餐食条件 */
    @ExcelColumn(value = "餐食条件",col = 12)
    private String breakType;

    /** 床铺类型 */
    @ExcelColumn(value = "床铺类型",col = 13)
    private String bedType;

//    /** 结算单人价 */
//    @ExcelColumn(value = "结算单人价",col = 14)
//    private String tPrice;

    @ExcelColumn(value = "入住人数",col = 14)
    private String personNum;

    @ExcelColumn(value = "结算总价",col = 15)
    private String totalTAmount;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
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

    public String getBreakType() {
        return breakType;
    }

    public void setBreakType(String breakType) {
        this.breakType = breakType;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

//    public String gettPrice() {
//        return tPrice;
//    }
//
//    public void settPrice(String tPrice) {
//        this.tPrice = tPrice;
//    }

    public String getPersonNum() {
        return personNum;
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }

    public String getTotalTAmount() {
        return totalTAmount;
    }

    public void setTotalTAmount(String totalTAmount) {
        this.totalTAmount = totalTAmount;
    }

    public String getInEnd() {
        return inEnd;
    }

    public void setInEnd(String inEnd) {
        this.inEnd = inEnd;
    }
}
