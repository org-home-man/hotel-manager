package com.hotel.admin.dto;

import com.hotel.admin.model.BizInv;
import com.hotel.admin.model.BizPrise;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BizProInv {
    private String	roomCode;
    private String  hotelCode;
    private String  provinceCode;
    private String  cityCode;
    private String  roomType;
    private String  bedType;
    private String stockYear;
    private String[]  stockDateInterval;
    private String  inventory;
    private String  isMonday;
    private String  isTuesday;
    private String  isThursday;
    private String  isFourday;
    private String  isFriday;
    private String  isSaterday;
    private String  isSunday;
    private List<BizInv> stockDateData;
    private List<Map> dateArray;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Map> getDateArray() {
        return dateArray;
    }

    public void setDateArray(List<Map> dateArray) {
        this.dateArray = dateArray;
    }

    public List<BizInv> getStockDateData() {
        return stockDateData;
    }

    public void setStockDateData(List<BizInv> stockDateData) {
        this.stockDateData = stockDateData;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getStockYear() {
        return stockYear;
    }

    public void setStockYear(String stockYear) {
        this.stockYear = stockYear;
    }

    public String[] getStockDateInterval() {
        return stockDateInterval;
    }

    public void setStockDateInterval(String[] stockDateInterval) {
        this.stockDateInterval = stockDateInterval;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getIsMonday() {
        return isMonday;
    }

    public void setIsMonday(String isMonday) {
        this.isMonday = isMonday;
    }

    public String getIsTuesday() {
        return isTuesday;
    }

    public void setIsTuesday(String isTuesday) {
        this.isTuesday = isTuesday;
    }

    public String getIsThursday() {
        return isThursday;
    }

    public void setIsThursday(String isThursday) {
        this.isThursday = isThursday;
    }

    public String getIsFourday() {
        return isFourday;
    }

    public void setIsFourday(String isFourday) {
        this.isFourday = isFourday;
    }

    public String getIsFriday() {
        return isFriday;
    }

    public void setIsFriday(String isFriday) {
        this.isFriday = isFriday;
    }

    public String getIsSaterday() {
        return isSaterday;
    }

    public void setIsSaterday(String isSaterday) {
        this.isSaterday = isSaterday;
    }

    public String getIsSunday() {
        return isSunday;
    }

    public void setIsSunday(String isSunday) {
        this.isSunday = isSunday;
    }

    @Override
    public String toString() {
        return "BizProInv{" +
                "roomCode='" + roomCode + '\'' +
                ", hotelCode='" + hotelCode + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", roomType='" + roomType + '\'' +
                ", bedType='" + bedType + '\'' +
                ", priceYear=" + stockYear +
                ", priceDateInterval=" + Arrays.toString(stockDateInterval) +
                ", inventory='" + inventory + '\'' +
                ", isMonday='" + isMonday + '\'' +
                ", isTuesday='" + isTuesday + '\'' +
                ", isThursday='" + isThursday + '\'' +
                ", isFourday='" + isFourday + '\'' +
                ", isFriday='" + isFriday + '\'' +
                ", isSaterday='" + isSaterday + '\'' +
                ", isSunday='" + isSunday + '\'' +
                ", priceDateData=" + stockDateData +
                ", dateArray=" + dateArray +
                ", date='" + date + '\'' +
                '}';
    }
}
