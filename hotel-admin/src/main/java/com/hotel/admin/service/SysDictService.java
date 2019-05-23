package com.hotel.admin.service;

import com.hotel.admin.dto.SysDictDto;
import com.hotel.admin.model.SysDict;
import com.hotel.core.page.Page;
import com.hotel.core.service.IService;

import java.util.List;

/**
 * 字典管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDictService extends IService<SysDict> {

	/**
	 * 分页查询字典
	 * @param sysDict
	 * @return
	 */
	Page findPage(SysDict sysDict);

	/**
	 * 通过编码查询子分类
	 * @param code
	 * @param locale 语言环境 zh_cn 中 en_us 英
	 * @return
	 */
	List<SysDictDto> findByCode(String code, String locale);
}
