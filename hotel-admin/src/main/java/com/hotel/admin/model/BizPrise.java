package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

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
public class BizPrise extends BusinessEntity{

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


	public List<BizPrise> getPriceDateData() {
		return priceDateData;
	}

	public void setPriceDateData(List<BizPrise> priceDateData) {
		this.priceDateData = priceDateData;
	}


}