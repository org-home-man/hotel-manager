package com.hotel.admin.dto;

import com.hotel.common.entity.BusinessEntity;

public class SysRoleEx extends BusinessEntity {

    private String name;

    private String remark;

    private Byte delFlag;

    private String roleId;

    private String roleIdKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Byte delFlag) {
        this.delFlag = delFlag;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleIdKey() {
        return roleIdKey;
    }

    public void setRoleIdKey(String roleIdKey) {
        this.roleIdKey = roleIdKey;
    }
}
