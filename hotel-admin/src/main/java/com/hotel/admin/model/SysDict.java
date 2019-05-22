package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;

public class SysDict extends BusinessEntity {

	/**
	 * 名称
	 */
	private String cnName;
	/**
	 * 名称
	 */
	private String enName;
	/**
	 * 字典对应编码 key 唯一
	 */
	private String code;

	/**
	 * 字典类型 1 顶级  2子级
	 */
    private String type;

	/**
	 * 字典 数据值 中文
	 */
	private String cnRemark;

	/**
	 * 字典 数据值 英文
	 */
	private String enRemark;

	/**
	 * 上级ID
	 */
	private String parentId;

	/**
	 * 描述
	 */
	private String remarks;

	/**
	 * 排序号
	 */
    private Long sort;

	public String getCnRemark() {
		return cnRemark;
	}

	public void setCnRemark(String cnRemark) {
		this.cnRemark = cnRemark;
	}

	public String getEnRemark() {
		return enRemark;
	}

	public void setEnRemark(String enRemark) {
		this.enRemark = enRemark;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
}