package com.hotel.admin.service.impl;

import java.util.List;

import com.hotel.admin.mapper.BizHotlMapper;
import com.hotel.admin.model.BizHotl;
import com.hotel.admin.service.BizHotlService;
import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-30 17:15:22
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class BizHotlServiceImpl implements BizHotlService {

	@Autowired
	private BizHotlMapper bizHotlMapper;

	@Override
	public int save(BizHotl record) {
		if(record.getHotelCode() == null || record.getHotelCode() == "0") {
			return bizHotlMapper.add(record);
		}
		return bizHotlMapper.update(record);
	}

	@Override
	public int delete(BizHotl record) {
		return bizHotlMapper.delete(record.getId());
	}

	@Override
	public int delete(List<BizHotl> records) {
		for(BizHotl record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public BizHotl findById(Long id) {
		return null;
	}

	@Override
	public BizHotl findById(String id) {
		return bizHotlMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, bizHotlMapper);
	}
	
}
