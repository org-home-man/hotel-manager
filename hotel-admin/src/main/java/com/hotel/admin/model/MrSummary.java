package com.hotel.admin.model;

import com.hotel.common.entity.Entity;

public class MrSummary extends Entity {
    /**
     * 
     */
    private String reportId;

    /**
     * 
     */
    private String reportTxt;

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
    private String creatTime;

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
     * @return report_txt 
     */
    public String getReportTxt() {
        return reportTxt;
    }

    /**
     * 
     * @param reportTxt 
     */
    public void setReportTxt(String reportTxt) {
        this.reportTxt = reportTxt == null ? null : reportTxt.trim();
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
     * @return creat_time 
     */
    public String getCreatTime() {
        return creatTime;
    }

    /**
     * 
     * @param creatTime 
     */
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }
}