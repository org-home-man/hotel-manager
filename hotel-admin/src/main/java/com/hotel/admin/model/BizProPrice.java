package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BizProPrice extends BusinessEntity{
    private String	roomCode;
    private String  hotelCode;
    private String  provinceCode;
    private String  cityCode;
    private String  roomType;
    private String  bedType;
    private String priceYear;
    private String[]  priceDateInterval;
    private String  sprice;
    private String  tprice;
    private String  sRoomPrice;
    private String  isMonday;
    private String  isTuesday;
    private String  isThursday;
    private String  isFourday;
    private String  isFriday;
    private String  isSaterday;
    private String  isSunday;
    private List<BizPrise> priceDateData;
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

    public List<BizPrise> getPriceDateData() {
        return priceDateData;
    }

    public void setPriceDateData(List<BizPrise> priceDateData) {
        this.priceDateData = priceDateData;
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

    public String getPriceYear() {
        return priceYear;
    }

    public void setPriceYear(String priceYear) {
        this.priceYear = priceYear;
    }

    public String[] getPriceDateInterval() {
        return priceDateInterval;
    }

    public void setPriceDateInterval(String[] priceDateInterval) {
        this.priceDateInterval = priceDateInterval;
    }

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public String getTprice() {
        return tprice;
    }

    public void setTprice(String tprice) {
        this.tprice = tprice;
    }

    public String getsRoomPrice() {
        return sRoomPrice;
    }

    public void setsRoomPrice(String sRoomPrice) {
        this.sRoomPrice = sRoomPrice;
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
        return "BizProPrice{" +
                "roomCode='" + roomCode + '\'' +
                ", hotelCode='" + hotelCode + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", roomType='" + roomType + '\'' +
                ", bedType='" + bedType + '\'' +
                ", priceYear='" + priceYear + '\'' +
                ", priceDateInterval=" + Arrays.toString(priceDateInterval) +
                ", sprice='" + sprice + '\'' +
                ", tprice='" + tprice + '\'' +
                ", sRoomPrice='" + sRoomPrice + '\'' +
                ", isMonday='" + isMonday + '\'' +
                ", isTuesday='" + isTuesday + '\'' +
                ", isThursday='" + isThursday + '\'' +
                ", isFourday='" + isFourday + '\'' +
                ", isFriday='" + isFriday + '\'' +
                ", isSaterday='" + isSaterday + '\'' +
                ", isSunday='" + isSunday + '\'' +
                ", priceDateData=" + priceDateData +
                ", dateArray=" + dateArray +
                ", date='" + date + '\'' +
                '}';
    }
}
