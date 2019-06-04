package com.hotel.admin.model;

import com.hotel.common.entity.Entity;

public class BizRecommendRoom extends Entity {

    private String roomCode;

    private String custroomType;

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
}
