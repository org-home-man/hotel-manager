package com.hotel.admin.service.impl;

import com.hotel.admin.constants.Constant;
import com.hotel.admin.constants.SysConstants;
import com.hotel.admin.dto.SysDictDto;
import com.hotel.admin.mapper.SysDictMapper;
import com.hotel.admin.model.SysDict;
import com.hotel.admin.service.SysDictService;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.page.Page;
import com.hotel.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictServiceImpl extends AbstractService<SysDict> implements SysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;


	@Override
	public Page findPage(SysDict sysDict) {
		sysDictMapper.select(sysDict);
		return PageContext.getPage();
	}

	@Override
	public int save(SysDict dict) {
		//校验是否存在相同的CODE
		if(Utils.isEmpty(dict.getCode())){
			throw new GlobalException("codeNotNull");
		}
		SysDict sysDict = sysDictMapper.validateUnique(dict);
		if(Utils.isNotEmpty(sysDict)){
			//校验当前ID是否存在
			if(!sysDict.getId().equals(dict.getId())){
				throw new GlobalException("codeExist");
			}
		}
		if(Utils.isEmpty(dict.getId())){
			return sysDictMapper.insertSelective(dict);
		}
		return sysDictMapper.updateByPrimaryKeySelective(dict);
	}

	@Override
	public List<SysDictDto> findByCode(String code, String locale) {
		if(SysConstants.CHINESE.equalsIgnoreCase(locale)){
			locale = Constant.BOOL_NO;
		}else if(SysConstants.ENGLISH.equals(locale)){
			locale = Constant.BOOL_YES;
		}else{
			locale = Constant.BOOL_NO;
		}
		List<SysDictDto> list = sysDictMapper.findByCode(code,locale);
		return list;
	}
}
