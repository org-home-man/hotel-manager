package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

/**
 * ---------------------------
 * 订单信息表 (BizPuchs)         
 * ---------------------------
 */
public class BizPuchsUpdate extends BusinessEntity {

	/** 订单号 */
	private String orderCode;
	/** 客房编号 */
	private String roomCode;
	/** 客房编号 */
	private String hotelCode;
	/** 确定时间 */
	private String confirmTime;
	/** 入住日期 */
	private String inDateStart;
	/** 退房日期 */
	private String outDateEnd;
	/** 代表者姓名 */
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
	private Integer aNum;
	/** 儿童数 */
	private Integer bNum;
	/** 幼儿数 */
	private Integer cNum;
	/** 房间数 */
	private Integer roomNum;
	/** 币种 */
	private String currency;
	/** 销售总价 */
	private Double totalSAmount;
	/** 备注 */
	private String remark;
	/** 订单状态 */
	private String status;
	/** 订单状态 */
	private String hotelName;

	/** 订单确认用户 */
	private String confirmName;

	/** 确认日期 */
	private String lastCrtTime;

	private String  confirmDate;

	private String local;

	private String roomType;

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getconfirmDatee() {
		return confirmDate;
	}

	public void setconfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
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

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getinDateStart() {
		return inDateStart;
	}

	public void setinDateStart(String inDateStart) {
		this.inDateStart = inDateStart;
	}

	public String getoutDateEnd() {
		return outDateEnd;
	}

	public void setoutDateEnd(String outDateEnd) {
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

	public Integer getaNum() {
		return aNum;
	}

	public void setaNum(Integer aNum) {
		this.aNum = aNum;
	}

	public Integer getbNum() {
		return bNum;
	}

	public void setbNum(Integer bNum) {
		this.bNum = bNum;
	}

	public Integer getcNum() {
		return cNum;
	}

	public void setcNum(Integer cNum) {
		this.cNum = cNum;
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getConfirmName() {
		return confirmName;
	}

	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	public String getLastCrtTime() {
		return lastCrtTime;
	}

	public void setLastCrtTime(String lastCrtTime) {
		this.lastCrtTime = lastCrtTime;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
}