package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

import java.util.Date;

/**
 * ---------------------------
 * 订单信息表 (BizPuchs)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizPuchs extends BusinessEntity {

	/**1 订单号 */
	private String orderCode;
	/**1 客房编号 */
	private String roomCode;
	/**1 确定时间 */
	private String lastCrtTime;
	/**1 入住日期 */
	private String inDateStart;
	/**1 退房日期 */
	private String outDateEnd;
	/**1 代表者姓名 */
	private String pName;
	/** 护照号 */
	private String passport;
	/** 出生年月 */
	private String birth;
	/** 联系电话 */
	private String phone;
	/** 联系人邮件 */
	private String emailAddress;
	/** 成人人数 */
	private Integer adultNum;
	/** 儿童数 */
	private Integer childNum;
	/** 房间数 */
	private Integer roomNum;
	/** 币种 */
	private String currency;
	/** 销售总价 */
	private Double totalSAmount;
	/** 结算总价 */
	private Double totalTAmount;
	/** 备注 */
	private String remark;
	/**1 订单状态 */
	private String status;
	/** 1酒店名称 */
	private String hotelName;
	/** 6-12岁儿童 */
	private String children612;
	/** 4-6岁儿童 */
	private String children46;
	/** 4岁以下儿童 */
	private String children4;

	private String hotelCode;


	public Double getTotalTAmount() {
		return totalTAmount;
	}

	public void setTotalTAmount(Double totalTAmount) {
		this.totalTAmount = totalTAmount;
	}

	public String getChildren612() {
		return children612;
	}

	public void setChildren612(String children612) {
		this.children612 = children612;
	}

	public String getChildren46() {
		return children46;
	}

	public void setChildren46(String children46) {
		this.children46 = children46;
	}

	public String getChildren4() {
		return children4;
	}

	public void setChildren4(String children4) {
		this.children4 = children4;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

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

	public String getLastCrtTime() {
		return lastCrtTime;
	}

	public void setLastCrtTime(String lastCrtTime) {
		this.lastCrtTime = lastCrtTime;
	}

	public String getInDateStart() {
		return inDateStart;
	}

	public void setInDateStart(String inDateStart) {
		this.inDateStart = inDateStart;
	}

	public String getOutDateEnd() {
		return outDateEnd;
	}

	public void setOutDateEnd(String outDateEnd) {
		this.outDateEnd = outDateEnd;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getAdultNum() {
		return adultNum;
	}

	public void setAdultNum(Integer adultNum) {
		this.adultNum = adultNum;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getTotalSAmount() {
		return totalSAmount;
	}

	public void setTotalSAmount(Double totalSAmount) {
		this.totalSAmount = totalSAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}