package com.hotel.admin.qo;

import com.hotel.common.entity.Entity;

public class BizPuchsQuery extends Entity {

    /** 订单号 */
    private String orderCode;
    /** 客房编号 */
    private String roomCode;
    /** 代表者姓名 */
    private String pName;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
