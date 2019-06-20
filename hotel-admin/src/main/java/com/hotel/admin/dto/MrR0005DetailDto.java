package com.hotel.admin.dto;

import com.hotel.common.bean.ExcelColumn;
import com.hotel.common.entity.Entity;

public class MrR0005DetailDto extends Entity {

    @ExcelColumn(value = "酒店名称",col = 1)
    private String hotelName;

    @ExcelColumn(value = "当月成交订单笔数",col = 2)
    private String orderNum;

    @ExcelColumn(value = "当月销售客房间数",col = 3)
    private String roomNum;

    @ExcelColumn(value = "当月销售总价",col = 4)
    private String totalSellAmt;

    @ExcelColumn(value = "月结算总价",col = 5)
    private String totalSettlementAmt;

    @ExcelColumn(value = "未结算总价",col = 6)
    private String pendingAmt;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getTotalSellAmt() {
        return totalSellAmt;
    }

    public void setTotalSellAmt(String totalSellAmt) {
        this.totalSellAmt = totalSellAmt;
    }

    public String getTotalSettlementAmt() {
        return totalSettlementAmt;
    }

    public void setTotalSettlementAmt(String totalSettlementAmt) {
        this.totalSettlementAmt = totalSettlementAmt;
    }

    public String getPendingAmt() {
        return pendingAmt;
    }

    public void setPendingAmt(String pendingAmt) {
        this.pendingAmt = pendingAmt;
    }
}
