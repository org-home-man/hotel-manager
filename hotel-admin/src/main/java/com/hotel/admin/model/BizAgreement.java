package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

public class BizAgreement extends BusinessEntity {

    private String lanType;

    private String remark;

    public String getLanType() {
        return lanType;
    }

    public void setLanType(String lanType) {
        this.lanType = lanType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
