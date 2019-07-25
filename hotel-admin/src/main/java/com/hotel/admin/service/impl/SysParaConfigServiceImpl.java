package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.HotelAreaMapper;
import com.hotel.admin.mapper.SysParaConfigMapper;
import com.hotel.admin.model.HotelArea;
import com.hotel.admin.model.SysParaConfig;
import com.hotel.admin.service.SysParaConfigService;
import com.hotel.core.context.PageContext;
import com.hotel.core.page.Page;
import com.hotel.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfigServiceImpl)
 * ---------------------------
 */
@Service
public class SysParaConfigServiceImpl extends AbstractService<SysParaConfig> implements SysParaConfigService {

	@Autowired
	private SysParaConfigMapper sysParaConfigMapper;
	@Autowired
	private HotelAreaMapper hotelAreaMapper;

	@Override
	public int save(SysParaConfig record) {
		if(record.getParaSubCode2() == null || record.getParaSubCode2() == "0") {
			return sysParaConfigMapper.insertSelective(record);
		}
		return sysParaConfigMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SysParaConfig> findByCode(SysParaConfig record){
		return sysParaConfigMapper.select(record);
	}


	@Override
	public Map<String,List> findKeyValue(SysParaConfig record) {
		List<SysParaConfig> lists =  sysParaConfigMapper.findKeyValue(record);
		List roomtypeLi = new ArrayList();
		List roomstyleLi = new ArrayList();
		List bedtypeLi = new ArrayList();
		List breaktypeLi = new ArrayList();
		List hoteltypeLi = new ArrayList();
		List hotelLevelLi = new ArrayList();
		Map<String,List> temp = new HashMap<String,	List>();
		for (int i=0;i<lists.size();i++) {
			SysParaConfig sysPara = lists.get(i);
			if (sysPara.getParaCode().contains("roomtype")) {
				roomtypeLi.add(sysPara);
			}
			if(sysPara.getParaCode().contains("roomstyle")) {
				roomstyleLi.add(sysPara);
			}
			if(sysPara.getParaCode().contains("bedtype")){
				bedtypeLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("breaktype")){
				breaktypeLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("hotelType")){

				hoteltypeLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("hotelLevel")){

				hotelLevelLi.add(sysPara);
			}
		}
		temp.put("roomtype",roomtypeLi);
		temp.put("roomstyle",roomstyleLi);
		temp.put("bedtype",bedtypeLi);
		temp.put("breaktype",breaktypeLi);
		temp.put("hotelType",hoteltypeLi);
		temp.put("hotelLevel",hotelLevelLi);
		return temp;
	}


	@Override
	public Map<String,List> findKeyValueHotel(SysParaConfig record) {
		List<SysParaConfig> lists =  sysParaConfigMapper.findKeyValue(record);
		List hoteltypeLi = new ArrayList();
		List hotelLevelLi = new ArrayList();
		List provinceLevelLi = new ArrayList();
		List cityCodeLevelLi = new ArrayList();
		Map<String,List> temp = new HashMap<String,	List>();
		for (int i=0;i<lists.size();i++) {
			SysParaConfig sysPara = lists.get(i);
			if (sysPara.getParaCode().contains("hotelType")){

				hoteltypeLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("hotelLevel")){

				hotelLevelLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("cityCode")){
				cityCodeLevelLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("provinceCode")){
				provinceLevelLi.add(sysPara);
			}
		}
		System.out.println("language =" + record.getlanguage());
		HotelArea hotelArea = new HotelArea();
		List<HotelArea> listsArea = null;
		List<HotelArea> listsArea1 = null;
		if("zh_cn".equals(record.getlanguage()) ) {
			hotelArea.setAreaLvl("1");
			listsArea = hotelAreaMapper.findCname(hotelArea);
			System.out.println("listsArea =" + listsArea.get(0).getAreaCname());

			hotelArea.setAreaLvl("2");
			listsArea1 = hotelAreaMapper.findCname(hotelArea);
			System.out.println("listsArea1 =" + listsArea1.get(0).getAreaCname());
		}
		else
		{
			hotelArea.setAreaLvl("1");
			listsArea = hotelAreaMapper.findEname(hotelArea);
			System.out.println("listsArea =" + listsArea.get(0).getAreaEname());

			hotelArea.setAreaLvl("2");
			listsArea1 = hotelAreaMapper.findEname(hotelArea);
			System.out.println("listsArea =" + listsArea1.get(0).getAreaEname());
		}
		temp.put("hotelType",hoteltypeLi);
		temp.put("hotelLevel",hotelLevelLi);
		temp.put("provinceCode",listsArea);
		temp.put("cityCode",listsArea1);

		//List<HotelArea>  listsArea =  hotelAreaMapper.findPage();
		 //crtIdMapper.findPage();

		return temp;
	}
	@Override
	public Page findPage() {
		sysParaConfigMapper.selectAll();
		return PageContext.getPage();
	}

	public Map<String,List> findKeyValueHotelRoom(SysParaConfig record) {
		List<SysParaConfig> lists =  sysParaConfigMapper.findKeyValue(record);
		List hoteltypeLi = new ArrayList();
		List hotelLevelLi = new ArrayList();
		List provinceLevelLi = new ArrayList();
		List cityCodeLevelLi = new ArrayList();
		List roomtypeLi = new ArrayList();
		List roomstyleLi = new ArrayList();
		List bedtypeLi = new ArrayList();
		List recommendedLi = new ArrayList();
		List breaktypeLi = new ArrayList();
		Map<String,List> temp = new HashMap<String,	List>();
		for (int i=0;i<lists.size();i++) {
			SysParaConfig sysPara = lists.get(i);
			if (sysPara.getParaCode().contains("hotelType")){

				hoteltypeLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("hotelLevel")){

				hotelLevelLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("cityCode")){
				cityCodeLevelLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("provinceCode")){
				provinceLevelLi.add(sysPara);
			}

			if (sysPara.getParaCode().contains("roomtype")) {
				roomtypeLi.add(sysPara);
			}
			if(sysPara.getParaCode().contains("roomstyle")) {
				roomstyleLi.add(sysPara);
			}
			if(sysPara.getParaCode().contains("bedtype")){
				bedtypeLi.add(sysPara);
			}
			if (sysPara.getParaCode().contains("breaktype")){
				breaktypeLi.add(sysPara);
			}
			if(sysPara.getParaCode().contains("recommended")) {
				recommendedLi.add(sysPara);
			}

		}
		temp.put("hotelType",hoteltypeLi);
		temp.put("hotelLevel",hotelLevelLi);
		temp.put("provinceCode",provinceLevelLi);
		temp.put("cityCode",cityCodeLevelLi);
		temp.put("roomtype",roomtypeLi);
		temp.put("roomstyle",roomstyleLi);
		temp.put("bedtype",bedtypeLi);
		temp.put("breaktype",breaktypeLi);
		temp.put("recommended",recommendedLi);

		//List<HotelArea>  listsArea =  hotelAreaMapper.findPage();
		//crtIdMapper.findPage();


		return temp;
	}

}
