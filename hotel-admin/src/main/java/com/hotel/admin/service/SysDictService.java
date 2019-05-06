package com.hotel.admin.service;

import java.util.List;

import com.hotel.admin.model.SysDict;
import com.hotel.core.page.Page;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.IService;

/**
 * 字典管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDictService extends IService<SysDict> {

	/**
	 * 根据名称查询
	 * @param label
	 * @return
	 */
	List<SysDict> findByLable(String label);

	/**
	 * 分页查询字典
	 * @param label
	 * @return
	 */
	Page findPage(String label);
}
