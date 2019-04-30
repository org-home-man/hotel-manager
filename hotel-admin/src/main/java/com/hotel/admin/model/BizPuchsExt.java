package com.hotel.admin.model;

/**
 * ---------------------------
 * 订单信息辅助表 (BizPuchsExt)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:34:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizPuchsExt {

	/** 订单号 */
	private String orderCode;
	/** 客房编号 */
	private String roomCode;
	/** 居住日期 */
	private java.util.Date liveDate;
	/** 销售价 */
	private Double sAmount;
	/** 结算价 */
	private Double tAmount;
	/** 创建人员 */
	private String creatBy;
	/** 创建时间 */
	private java.util.Date creatTime;
	/** 更新时间 */
	private String lastUpdateBy;
	/** 更新时间 */
	private java.util.Date lastUpdateTime;

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

	public java.util.Date getLiveDate() {
		return liveDate;
	}

	public void setLiveDate(java.util.Date liveDate) {
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