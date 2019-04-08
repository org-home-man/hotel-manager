package com.hotel.admin.model;

/**
 * ---------------------------
 * 客房库存表 (BizInv)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-08 16:15:59
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizInv {

	/** 客房编号 */
	private String roomCode;
	/** 库存日期 */
	private String invDate;
	/** 库存数量 */
	private Integer inventory;
	/** 是否自动关闭 Y 到期自动关闭 N到期不自动关闭 */
	private String autoClose;
	/** 创建人员 */
	private String creatBy;
	/** 创建时间 */
	private java.util.Date creatTime;
	/** 更新时间 */
	private String lastUpdateBy;
	/** 更新时间 */
	private java.util.Date lastUpdateTime;

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getAutoClose() {
		return autoClose;
	}

	public void setAutoClose(String autoClose) {
		this.autoClose = autoClose;
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