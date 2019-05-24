package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

public class SysUserRole extends BusinessEntity {

    private Long userId;

    private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "SysUserRole{" +
				"userId=" + userId +
				", roleId=" + roleId +
				'}';
	}
}