package com.hotel.admin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SysDictDto {

	@JsonIgnore
	private String parCode;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 字典对应编码 key 唯一
	 */
	private String code;

	public String getParCode() {
		return parCode;
	}

	public void setParCode(String parCode) {
		this.parCode = parCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}