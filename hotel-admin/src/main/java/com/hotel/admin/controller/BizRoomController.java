package com.hotel.admin.controller;

import com.hotel.admin.dto.BizRoomQuery;
import com.hotel.admin.model.BizProPrice;
import com.hotel.admin.model.BizRoom;
import com.hotel.admin.service.BizRoomService;
import com.hotel.core.http.HttpResult;
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
@RequestMapping("bizRoom")
public class BizRoomController {

	@Autowired
	private BizRoomService bizRoomService;

	/**
	 * 保存客房信息表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody BizRoom record) {
		return HttpResult.ok(bizRoomService.save(record));
	}

    /**
     * 删除客房信息表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizRoom> records) {
		return HttpResult.ok(bizRoomService.delete(records));
	}

    /**
     * 基础分页查询
     * @param bizRoomQuery 条件查询对象
     * @return
     */    
	@PostMapping(value="/page")
	public HttpResult findPage(@RequestBody BizRoomQuery bizRoomQuery) {
		return HttpResult.ok(bizRoomService.findPagePara(bizRoomQuery));
	}


	/**
	 * 上传宣传图片
	 * @return
	 */
	@RequestMapping(value="/uploadFile")
	public String uploadFile(@RequestParam(name="file") MultipartFile uploadFile,HttpServletRequest request)  {
		String rootPath = request.getServletContext().getRealPath("/upload");
//		System.out.println(str);
//		String rootPath = "D:\\upload";
		System.out.println("rootPath:"+rootPath);
		if(uploadFile != null) {
			//获取上传文件的名字
			String fileName = uploadFile.getOriginalFilename();
			System.out.println("上传文件的名字:"+fileName);
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			if(!( "jpg".equals(suffix) || "png".equals(suffix)) ){

			}
			String tempFileName = UUID.randomUUID().toString()+suffix;
			File fileTemp = new File(rootPath);
			if(!fileTemp.exists()) {
				fileTemp.mkdirs();
			}
			File file = new File(rootPath+File.separator+tempFileName);

			try {
				uploadFile.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			request.setAttribute("uploadFilePath",rootPath+File.separator+tempFileName);

			return rootPath+File.separator+tempFileName;
		}
		return  null;
	}

	/**
	 * 根据主键查询
	 * @param
	 * @return
	 */
	@PostMapping(value="/producePriceCalendar")
	public HttpResult producePriceDateData(@RequestBody BizProPrice bizProPrice) {
		return HttpResult.ok(bizRoomService.producePriceCalendar(bizProPrice));
	}

	/**
	 * 生成用户输入的牌价数据，根据输入的和之前的做对比
	 *
	 * @return
	 */
	@PostMapping(value="/priceDatePro")
	public HttpResult priceDatePro(@RequestBody BizProPrice bizProPrice) {
		return HttpResult.ok(bizRoomService.productDatePrice(bizProPrice));
	}

}
