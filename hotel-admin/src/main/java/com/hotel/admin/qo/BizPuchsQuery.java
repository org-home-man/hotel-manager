package com.hotel.admin.qo;

import com.hotel.common.entity.BusinessEntity;

import java.util.Date;

public class BizPuchsQuery extends BusinessEntity {

    /** 订单号 */
    private String orderCode;
    /** 订单状态 */
    private String roomStatus;
    /** 酒店名称 */
    private String hotelName;
    /** 确认开始时间 */
    private Date confirmTimeStart;
    /** 确认结束时间 */
    private Date confirmTimeEnd;

    private Date createTimeStart;

    private Date createTimeEnd;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getConfirmTimeStart() {
        return confirmTimeStart;
    }

    public void setConfirmTimeStart(Date confirmTimeStart) {
        this.confirmTimeStart = confirmTimeStart;
    }

    public Date getConfirmTimeEnd() {
        return confirmTimeEnd;
    }

    public void setConfirmTimeEnd(Date confirmTimeEnd) {
        this.confirmTimeEnd = confirmTimeEnd;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
