package com.hotel.admin.model;

import com.hotel.common.entity.Entity;

import javax.persistence.Transient;

public class MrDetail extends Entity {
    /**
     * 订单编号
     */
    private String reportId;
    /**
     * 订单月份
     */
    private String reportMonth;

    /**
     * 批次
     */
    private String reportSeq;

    /**
     * 订单编号
     */
    private String orderCode;

    private String hotelCode;
    private String createName;
    private Integer createId;

    private String hotelCname;
    private String deptName;
    private String createTimeStart;

    private String createTimeEnd;

    @Transient
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    private String hotelEname;
    private Integer orderNum;
    /**
     * 房间编号
     */
    private Integer deptId;
    /**
     * 房间数
     */
    private Integer roomNum;
    private Double totalSellAmt;

    private Double totalSettlementAmt;

    private Double pendingAmt;

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public void setHotelCname(String hotelCname) {
        this.hotelCname = hotelCname;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setHotelEname(String hotelEname) {
        this.hotelEname = hotelEname;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setTotalSellAmt(Double totalSellAmt) {
        this.totalSellAmt = totalSellAmt;
    }

    public void setTotalSettlementAmt(Double totalSettlementAmt) {
        this.totalSettlementAmt = totalSettlementAmt;
    }

    public void setPendingAmt(Double pendingAmt) {
        this.pendingAmt = pendingAmt;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public String getHotelCname() {
        return hotelCname;
    }

    public String getDeptName() {
        return deptName;
    }

    public String getHotelEname() {
        return hotelEname;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public Double getTotalSellAmt() {
        return totalSellAmt;
    }

    public Double getTotalSettlementAmt() {
        return totalSettlementAmt;
    }

    public Double getPendingAmt() {
        return pendingAmt;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getReportMonth() {
        return reportMonth;
    }


    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth == null ? null : reportMonth.trim();
    }


    public String getReportSeq() {
        return reportSeq;
    }


    public void setReportSeq(String reportSeq) {
        this.reportSeq = reportSeq == null ? null : reportSeq.trim();
    }


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }


    public Integer getRoomNum() {
        return roomNum;
    }


    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

}