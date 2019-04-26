package com.hotel.admin.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 客房牌价表 (BizPrise)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizPrise {

	/** 客房编号 */
	private String roomCode;
	/** 房间类型 */
	private String roomType;
	/** 牌价日期 */
	private String priceDate;
	/** 销售价格 */
	private Double sPrice;
	/** 结算价格 */
	private Double tPrice;
	/** 销售房价 */
	private Double sRoomPrice;
	/** 结算房价 */
	private Double tRoomPrice;

	/** 批量插入数据 */
	private List<BizPrise> priceDateData;

	private String operaBy;

	private Date operaTime;

	/** 创建人员 */
	private String creatBy;
	/** 创建时间 */
	private java.util.Date creatTime;
	/** 更新时间 */
	private String lastUpdateBy;
	/** 更新时间 */
	private java.util.Date lastUpdateTime;

	public String getOperaBy() {
		return operaBy;
	}

	public void setOperaBy(String operaBy) {
		this.operaBy = operaBy;
	}

	public Date getOperaTime() {
		return operaTime;
	}

	public void setOperaTime(Date operaTime) {
		this.operaTime = operaTime;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}

	public Double getSPrice() {
		return sPrice;
	}

	public void setSPrice(Double sPrice) {
		this.sPrice = sPrice;
	}

	public Double getTPrice() {
		return tPrice;
	}

	public void setTPrice(Double tPrice) {
		this.tPrice = tPrice;
	}

	public Double getSRoomPrice() {
		return sRoomPrice;
	}

	public void setSRoomPrice(Double sRoomPrice) {
		this.sRoomPrice = sRoomPrice;
	}

	public Double getTRoomPrice() {
		return tRoomPrice;
	}

	public void setTRoomPrice(Double tRoomPrice) {
		this.tRoomPrice = tRoomPrice;
	}

	public String getCreatBy() {
		return creatBy;
	}

	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}

	public java.util.Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(java.util.Date creatTime) {
		this.creatTime = creatTime;
	}

	public List<BizPrise> getPriceDateData() {
		return priceDateData;
	}

	public void setPriceDateData(List<BizPrise> priceDateData) {
		this.priceDateData = priceDateData;
	}

	@Override
	public String toString() {
		return "BizPrise{" +
				"roomCode='" + roomCode + '\'' +
				", roomType='" + roomType + '\'' +
				", priceDate='" + priceDate + '\'' +
				", sPrice=" + sPrice +
				", tPrice=" + tPrice +
				", sRoomPrice=" + sRoomPrice +
				", tRoomPrice=" + tRoomPrice +
				", creatBy='" + creatBy + '\'' +
				", creatTime=" + creatTime +
				", lastUpdateBy='" + lastUpdateBy + '\'' +
				", lastUpdateTime=" + lastUpdateTime +
				'}';
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public java.util.Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(java.util.Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}