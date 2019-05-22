package com.hotel.admin.service;

import com.hotel.admin.model.SysDict;
import com.hotel.core.page.Page;
import com.hotel.core.service.IService;

/**
 * 字典管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDictService extends IService<SysDict> {

	/**
	 * 分页查询字典
	 * @param label
	 * @return
	 */
	Page findPage(String label);
}
