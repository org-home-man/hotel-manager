package com.hotel.admin.model;

import com.hotel.common.entity.Entity;

/**
 * ---------------------------
 * 订单信息表 (BizPuchs)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-30 12:29:00
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
public class BizPuchs extends Entity {

	/** 订单号 */
	private String orderCode;
	/** 客房编号 */
	private String roomCode;
	/** 入住日期 */
	private String inDateStart;
	/** 退房日期 */
	private String outDateEnd;
	/** 入住日期 */
	private java.util.Date inDate;
	/** 退房日期 */
	private java.util.Date outDate;
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
	/** 创建人员 */
	private String creatBy;
	/** 创建时间 */
	private java.util.Date creatTime;
	/** 更新时间 */
	private String lastUpdateBy;

	public Integer getcNum() {
		return cNum;
	}

	public void setcNum(Integer cNum) {
		this.cNum = cNum;
	}

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

	public java.util.Date getinDate() {
		return inDate;
	}

	public void setinDate(java.util.Date inDate) {
		this.inDate = inDate;
	}

	public java.util.Date getoutDate() {
		return outDate;
	}

	public void setOutDate(java.util.Date outDate) {
		this.outDate = outDate;
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

	public void setOutDateEnd(String outDateEnd) {
		this.outDateEnd = outDateEnd;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
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

	public Integer getANum() {
		return aNum;
	}

	public void setANum(Integer aNum) {
		this.aNum = aNum;
	}

	public Integer getBNum() {
		return bNum;
	}

	public void setBNum(Integer bNum) {
		this.bNum = bNum;
	}

	public Integer getCNum() {
		return cNum;
	}

	public void setCNum(Integer cNum) {
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

	@Override
	public String toString() {
		return "BizPuchs{" +
				"orderCode='" + orderCode + '\'' +
				", roomCode='" + roomCode + '\'' +
				", inDateStart=" + inDateStart +
				", outDateEnd=" + outDateEnd +
				", inDate=" + inDate +
				", outDate=" + outDate +
				", pName='" + pName + '\'' +
				", passport='" + passport + '\'' +
				", birth='" + birth + '\'' +
				", phone='" + phone + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", aNum=" + aNum +
				", bNum=" + bNum +
				", cNum=" + cNum +
				", roomNum=" + roomNum +
				", currency='" + currency + '\'' +
				", totalSAmount=" + totalSAmount +
				", remark='" + remark + '\'' +
				", status='" + status + '\'' +
				", creatBy='" + creatBy + '\'' +
				", creatTime=" + creatTime +
				", lastUpdateBy='" + lastUpdateBy + '\'' +
				", lastUpdateTime=" + lastUpdateTime +
				'}';
	}
}