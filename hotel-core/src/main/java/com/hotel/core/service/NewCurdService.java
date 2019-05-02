package com.hotel.core.service;

import com.hotel.core.page.Page;
import com.hotel.core.page.PageRequest;

import java.util.List;

/**
 * 通用CURD接口
 */
public interface NewCurdService<T> {
	
	/**
	 * 保存操作
	 * @param record
	 * @return
	 */
	int save(T record);
	
	/**
	 * 删除操作
	 * @param record
	 * @return
	 */
	int delete(T record);
	
	/**
	 * 批量删除操作
	 * @param
	 */
	int delete(List<T> records);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	T findById(Long id);

}