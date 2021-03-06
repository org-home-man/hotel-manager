package com.hotel.admin.model;

import com.hotel.common.entity.Entity;

public class MrOrderdetail extends Entity {
    /**
     * 
     */
    private String reportId;

    /**
     * 
     */
    private String reportMonth;

    /**
     * 
     */
    private String reportSeq;

    /**
     * 
     */
    private String orderCode;

    /**
     * 
     */
    private String hotelCode;

    /**
     * 
     */
    private String hotelCname;

    /**
     * 
     */
    private String hotelEname;

    /**
     * 
     */
    private String roomCode;

    /**
     * 
     */
    private String inDateStart;

    /**
     * 
     */
    private String outDateEnd;

    /**
     * 
     */
    private String pName;

    /**
     * 
     */
    private String passport;

    /**
     * 
     */
    private String birth;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String emailAddress;

    /**
     * 
     */
    private Integer adultNum;

    /**
     * 
     */
    private Integer childNum;

    /**
     * 
     */
    private Integer cNum;

    /**
     * 
     */
    private Integer roomNum;

    /**
     * 
     */
    private String currency;

    /**
     * 
     */
    private Double totalSAmount;

    /**
     * 
     */
    private Double totalTAmount;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private String status;

    /**
     * 
     */
    private Long createId;

    /**
     * 
     */
    private String createName;

    /**
     * 
     */
    private Long deptId;

    /**
     * 
     */
    private String deptName;

    /**
     * 
     */
    private String createTime;

    /**
     * 
     */
    private String updateName;

    /**
     * 
     */
    private String updateTime;

    /**
     * 
     */
    private String lastCrtTime;

    /**
     * 
     */
    private String roomType;

    /**
     * 
     */
    private Integer popNum;

    /**
     * 
     */
    private Integer dayNum;

    private String createTimeStart;

    private String createTimeEnd;

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
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

    /**
     * 
     * @return report_id 
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * 
     * @param reportId 
     */
    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    /**
     * 
     * @return report_month 
     */
    public String getReportMonth() {
        return reportMonth;
    }

    /**
     * 
     * @param reportMonth 
     */
    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth == null ? null : reportMonth.trim();
    }

    /**
     * 
     * @return report_seq 
     */
    public String getReportSeq() {
        return reportSeq;
    }

    /**
     * 
     * @param reportSeq 
     */
    public void setReportSeq(String reportSeq) {
        this.reportSeq = reportSeq == null ? null : reportSeq.trim();
    }

    /**
     * 
     * @return order_code 
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 
     * @param orderCode 
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 
     * @return hotel_code 
     */
    public String getHotelCode() {
        return hotelCode;
    }

    /**
     * 
     * @param hotelCode 
     */
    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode == null ? null : hotelCode.trim();
    }

    /**
     * 
     * @return hotel_cname 
     */
    public String getHotelCname() {
        return hotelCname;
    }

    /**
     * 
     * @param hotelCname 
     */
    public void setHotelCname(String hotelCname) {
        this.hotelCname = hotelCname == null ? null : hotelCname.trim();
    }

    /**
     * 
     * @return hotel_ename 
     */
    public String getHotelEname() {
        return hotelEname;
    }

    /**
     * 
     * @param hotelEname 
     */
    public void setHotelEname(String hotelEname) {
        this.hotelEname = hotelEname == null ? null : hotelEname.trim();
    }

    /**
     * 
     * @return room_code 
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * 
     * @param roomCode 
     */
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    /**
     * 
     * @return in_date_start 
     */
    public String getInDateStart() {
        return inDateStart;
    }

    /**
     * 
     * @param inDateStart 
     */
    public void setInDateStart(String inDateStart) {
        this.inDateStart = inDateStart == null ? null : inDateStart.trim();
    }

    /**
     * 
     * @return out_date_end 
     */
    public String getOutDateEnd() {
        return outDateEnd;
    }

    /**
     * 
     * @param outDateEnd 
     */
    public void setOutDateEnd(String outDateEnd) {
        this.outDateEnd = outDateEnd == null ? null : outDateEnd.trim();
    }

    /**
     * 
     * @return p_name 
     */
    public String getpName() {
        return pName;
    }

    /**
     * 
     * @param pName 
     */
    public void setpName(String pName) {
        this.pName = pName == null ? null : pName.trim();
    }

    /**
     * 
     * @return passport 
     */
    public String getPassport() {
        return passport;
    }

    /**
     * 
     * @param passport 
     */
    public void setPassport(String passport) {
        this.passport = passport == null ? null : passport.trim();
    }

    /**
     * 
     * @return birth 
     */
    public String getBirth() {
        return birth;
    }

    /**
     * 
     * @param birth 
     */
    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    /**
     * 
     * @return phone 
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone 
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 
     * @return email_address 
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * 
     * @param emailAddress 
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    /**
     * 
     * @return adult_num 
     */
    public Integer getAdultNum() {
        return adultNum;
    }

    /**
     * 
     * @param adultNum 
     */
    public void setAdultNum(Integer adultNum) {
        this.adultNum = adultNum;
    }

    /**
     * 
     * @return child_num 
     */
    public Integer getChildNum() {
        return childNum;
    }

    /**
     * 
     * @param childNum 
     */
    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }

    /**
     * 
     * @return c_num 
     */
    public Integer getcNum() {
        return cNum;
    }

    /**
     * 
     * @param cNum 
     */
    public void setcNum(Integer cNum) {
        this.cNum = cNum;
    }

    /**
     * 
     * @return room_num 
     */
    public Integer getRoomNum() {
        return roomNum;
    }

    /**
     * 
     * @param roomNum 
     */
    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * 
     * @return currency 
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency 
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     * 
     * @return total_s_amount 
     */
    public Double getTotalSAmount() {
        return totalSAmount;
    }

    /**
     * 
     * @param totalSAmount 
     */
    public void setTotalSAmount(Double totalSAmount) {
        this.totalSAmount = totalSAmount;
    }

    /**
     * 
     * @return total_t_amount 
     */
    public Double getTotalTAmount() {
        return totalTAmount;
    }

    /**
     * 
     * @param totalTAmount 
     */
    public void setTotalTAmount(Double totalTAmount) {
        this.totalTAmount = totalTAmount;
    }

    /**
     * 
     * @return remark 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 
     * @return status 
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status 
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 
     * @return create_id 
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * 
     * @param createId 
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * 
     * @return create_name 
     */
    public String getCreateName() {
        return createName;
    }

    /**
     * 
     * @param createName 
     */
    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    /**
     * 
     * @return dept_id 
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 
     * @param deptId 
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 
     * @return dept_name 
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 
     * @param deptName 
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * 
     * @return create_time 
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * 
     * @return update_name 
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * 
     * @param updateName 
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    /**
     * 
     * @return update_time 
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime 
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * 
     * @return last_crt_time 
     */
    public String getLastCrtTime() {
        return lastCrtTime;
    }

    /**
     * 
     * @param lastCrtTime 
     */
    public void setLastCrtTime(String lastCrtTime) {
        this.lastCrtTime = lastCrtTime == null ? null : lastCrtTime.trim();
    }

    /**
     * 
     * @return room_type 
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * 
     * @param roomType 
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType == null ? null : roomType.trim();
    }

    /**
     * 
     * @return pop_num 
     */
    public Integer getPopNum() {
        return popNum;
    }

    /**
     * 
     * @param popNum 
     */
    public void setPopNum(Integer popNum) {
        this.popNum = popNum;
    }

}