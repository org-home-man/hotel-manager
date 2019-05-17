package com.hotel.admin.service;

import java.util.List;

import com.hotel.admin.model.SysDept;
import com.hotel.core.page.Page;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.IService;

/**
 * 机构管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDeptService extends IService<SysDept> {

	/**
	 * 查询机构树
	 * @return
	 */
	List<SysDept> findTree();

	int deleteBatch(List<SysDept> record);
}
