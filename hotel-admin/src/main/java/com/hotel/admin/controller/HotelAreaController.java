package com.hotel.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;

import com.hotel.admin.model.HotelArea;
import com.hotel.admin.service.HotelAreaService;

/**
 * ---------------------------
 * 地区码表 (HotelAreaController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-13 16:24:13
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("hotelArea")
public class HotelAreaController {

	@Autowired
	private HotelAreaService hotelAreaService;

	/**
	 * 保存地区码表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody HotelArea record) {
		return HttpResult.ok(hotelAreaService.save(record));
	}

    /**
     * 删除地区码表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<HotelArea> records) {
		return HttpResult.ok(hotelAreaService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(hotelAreaService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param areaCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam String areaCode) {
		return HttpResult.ok(hotelAreaService.findById(areaCode));
	}

	/**
	 * 基础分页查询
	 * @param pageRequest
	 * @return
	 */
	@PostMapping(value="/findAreaPage")
	public HttpResult findAreaPage(@RequestBody PageRequest pageRequest) {
		System.out.println("licy1");
		return HttpResult.ok(hotelAreaService.findAreaPage(pageRequest));
	}
}
