package com.hotel.admin.controller;

import com.hotel.admin.model.BizRoom;
import com.hotel.admin.service.BizRoomService;
import com.hotel.admin.service.HotelRoomService;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * ---------------------------
 * 客房信息表 (BizRoomController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-04-01 21:00:17
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("hotelRoom")
public class HotelRoomController {

	@Autowired
	private HotelRoomService hotelRoomService;


    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(hotelRoomService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(@RequestParam String roomCode) {
		return HttpResult.ok(hotelRoomService.findById(roomCode));
	}

}
