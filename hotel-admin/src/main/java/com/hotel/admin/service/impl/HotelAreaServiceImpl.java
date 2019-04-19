package com.hotel.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotel.common.utils.StringUtils;
import com.hotel.core.page.ColumnFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.admin.model.HotelArea;
import com.hotel.admin.mapper.HotelAreaMapper;
import com.hotel.admin.service.HotelAreaService;

import com.hotel.core.page.MybatisPageHelper;
import com.hotel.core.page.PageRequest;
import com.hotel.core.page.PageResult;

/**
 * ---------------------------
 * 地区码表 (HotelAreaServiceImpl)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-13 16:24:13
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@Service
public class HotelAreaServiceImpl implements HotelAreaService {

	@Autowired
	private HotelAreaMapper hotelAreaMapper;
	@Override
	public HotelArea findById(Long id) {
		return null;
	}


	@Override
	public int save(HotelArea record) {
		if(record.getAreaCode() == null || record.getAreaCode() == "0") {
			return hotelAreaMapper.add(record);
		}
		return hotelAreaMapper.update(record);
	}

	@Override
	public int delete(HotelArea record) {
		return hotelAreaMapper.delete(record.getAreaCode());
	}

	@Override
	public int delete(List<HotelArea> records) {
		for(HotelArea record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public HotelArea findById(String id) {
		return hotelAreaMapper.findById(id);
	}


	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, hotelAreaMapper);
	}

	@Override
	public  PageResult findAreaPage(PageRequest pageRequest) {
		System.out.println("licy2");
//		return MybatisPageHelper.findPage(pageRequest, hotelAreaMapper);
		Map<String,Object> map = new HashMap<String ,Object>();
		Map<String, ColumnFilter> temp = pageRequest.getColumnFilters();
		for (Map.Entry<String,ColumnFilter> entry : temp.entrySet() ) {
			ColumnFilter columnFilter = entry.getValue();
			if (!StringUtils.isBlank(columnFilter.getValue())) {
				map.put(columnFilter.getName(),columnFilter.getValue());
			}
		}


		System.out.println("licy3");
		PageResult pr =  MybatisPageHelper.findPage(pageRequest, hotelAreaMapper,"findAreaPage",map);
		System.out.println("licy4");
		System.out.println(pr.getContent());
		return pr;
	}
//	public PageResult findAreaPage(PageRequest pageRequest) {
//		return MybatisPageHelper.findAreaPage(pageRequest, hotelAreaMapper,"findAreaPage",map);
//	}
}
