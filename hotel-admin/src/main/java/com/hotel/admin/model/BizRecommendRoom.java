package com.hotel.admin.model;

import com.hotel.common.entity.Entity;

import javax.persistence.Transient;

public class BizRecommendRoom extends Entity {

    private String roomCode;

    private String custroomType;

    @Transient
    private String remark;

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getCustroomType() {
        return custroomType;
    }

    public void setCustroomType(String custroomType) {
        this.custroomType = custroomType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
