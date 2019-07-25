package com.hotel.admin.service.impl;

import java.util.List;
import com.hotel.admin.model.CrtId;
import com.hotel.admin.service.CrtIdService;
import com.hotel.admin.mapper.CrtIdMapper;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ---------------------------
 * 自增序列表 (CrtIdServiceImpl)         
 * ---------------------------
 */
@Service
public class CrtIdServiceImpl implements CrtIdService {

	@Autowired
	private CrtIdMapper crtIdMapper;

	@Override
	public int save(CrtId record) {
		if(record.getCrtType() == null || record.getCrtType() == "0") {
			return crtIdMapper.add(record);
		}
		return crtIdMapper.update(record);
	}

	@Override
	public int delete(CrtId record) {
		return crtIdMapper.delete(record.getCrtType());
	}

	@Override
	public int delete(List<CrtId> records) {
		for(CrtId record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public CrtId findById(Long id) {
		return null;
	}
	@Override
	public CrtId findById(String id) {
		return crtIdMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, crtIdMapper);
	}
	
}
