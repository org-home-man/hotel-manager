package com.hotel.admin.qo;

import com.hotel.common.entity.BusinessEntity;

public class BizPuchsQuery extends BusinessEntity {

    /** 订单号 */
    private String orderCode;
    /** 订单状态 */
    private String roomStatus;
    /** 酒店名称 */
    private String hotelName;
    /** 确认开始时间 */
    private String confirmTimeStart;
    /** 确认结束时间 */
    private String confirmTimeEnd;

    private String createTimeStart;

    private String createTimeEnd;

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

    public String getConfirmTimeStart() {
        return confirmTimeStart;
    }

    public void setConfirmTimeStart(String confirmTimeStart) {
        this.confirmTimeStart = confirmTimeStart;
    }

    public String getConfirmTimeEnd() {
        return confirmTimeEnd;
    }

    public void setConfirmTimeEnd(String confirmTimeEnd) {
        this.confirmTimeEnd = confirmTimeEnd;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
