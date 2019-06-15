package com.hotel.admin.dto;

import com.hotel.common.bean.ExcelColumn;
import com.hotel.common.entity.BusinessEntity;
import com.hotel.common.entity.Entity;

public class WrDetailDto extends Entity{

    @ExcelColumn(value = "订单编号",col = 1)
    private String orderCode;

    @ExcelColumn(value = "酒店名称",col = 2)
    private String hotelName;

    @ExcelColumn(value = "房间类型",col = 3)
    private String roomType;

    @ExcelColumn(value = "入住时间",col = 4)
    private String inDateStart;

    @ExcelColumn(value = "销售总价",col = 5)
    private String totalSAmount;

    @ExcelColumn(value = "订单产生日期",col = 6)
    private String createTime;

    @ExcelColumn(value = "订单产生用户",col = 7)
    private String createName;

    @ExcelColumn(value = "最终修改日期",col = 8)
    private String updateTime;

    @ExcelColumn(value = "最终修改用户",col = 9)
    private String updateName;

    @ExcelColumn(value = "订单状态",col = 10)
    private String status;

    private String deptName;


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getInDateStart() {
        return inDateStart;
    }

    public void setInDateStart(String inDateStart) {
        this.inDateStart = inDateStart;
    }

    public String getTotalSAmount() {
        return totalSAmount;
    }

    public void setTotalSAmount(String totalSAmount) {
        this.totalSAmount = totalSAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
