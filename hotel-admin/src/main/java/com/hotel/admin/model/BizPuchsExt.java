package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

/**
 * ---------------------------
 * 订单信息辅助表 (BizPuchsExt)         
 * ---------------------------
 */
public class BizPuchsExt extends BusinessEntity {

	/** 订单号 */
	private String orderCode;
	/** 客房编号 */
	private String roomCode;
	/** 居住日期 */
	private String liveDate;
	/** 销售价 */
	private Double sAmount;
	/** 结算价 */
	private Double tAmount;

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

	public String getLiveDate() {
		return liveDate;
	}

	public void setLiveDate(String liveDate) {
		this.liveDate = liveDate;
	}

	public Double getSAmount() {
		return sAmount;
	}

	public void setSAmount(Double sAmount) {
		this.sAmount = sAmount;
	}

	public Double getTAmount() {
		return tAmount;
	}

	public void setTAmount(Double tAmount) {
		this.tAmount = tAmount;
	}

}