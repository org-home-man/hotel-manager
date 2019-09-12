package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

import java.util.List;

/**
 * ---------------------------
 * 客房库存表 (BizInv)         
 * ---------------------------
 */
public class BizInv extends BusinessEntity {

	/** 客房编号 */
	private String hotelCode;
	/** 库存日期 */
	private String invDate;
	/** 库存数量 */
	private Integer inventory;
	/** 是否自动关闭 Y 到期自动关闭 N到期不自动关闭 */
	private String autoClose;

	/** 批量插入数据 */
	private List<BizInv> stockDateData;

	public List<BizInv> getStockDateData() {
		return stockDateData;
	}

	public void setStockDateData(List<BizInv> stockDateData) {
		this.stockDateData = stockDateData;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
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

	@Override
	public String toString() {
		return "BizInv{" +
				"hotelCode='" + hotelCode + '\'' +
				", invDate='" + invDate + '\'' +
				", inventory=" + inventory +
				", autoClose='" + autoClose + '\'' +
				", stockDateData=" + stockDateData +
				'}';
	}
}