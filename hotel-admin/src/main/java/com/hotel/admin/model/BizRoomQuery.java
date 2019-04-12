package com.hotel.admin.model;

import java.util.List;

/**
 * ---------------------------
 * 客房信息表 (BizRoomQuery)
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-01 21:00:17
 * 说明：  查询条件
 * ---------------------------
 */
public class BizRoomQuery {

	/** 客房编号 */
	private String roomCode;
	/** 酒店编码 */
	private String hotelCode;
	//酒店名称
	private String hotelName;
	/** 房间类型 */
	private String roomType;
	/** 床铺类型 */
	private String bedType;
	/** 餐食条件 */
	private String breakType;
	/** 默认库存数 */
	private Integer roomStock;


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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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

	public String getBreakType() {
		return breakType;
	}

	public void setBreakType(String breakType) {
		this.breakType = breakType;
	}

	public Integer getRoomStock() {
		return roomStock;
	}

	public void setRoomStock(Integer roomStock) {
		this.roomStock = roomStock;
	}
}