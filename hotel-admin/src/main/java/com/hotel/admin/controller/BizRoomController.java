package com.hotel.admin.controller;

import com.hotel.admin.dto.BizProInv;
import com.hotel.admin.dto.BizRoomQuery;
import com.hotel.admin.model.BizProPrice;
import com.hotel.admin.model.BizRoom;
import com.hotel.admin.service.BizRoomService;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ---------------------------
 * 客房信息表 (BizRoomController)         
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
	@SystemControllerLog(description = "客房信息编辑/保存")
	public HttpResult save(BizRoom record) {
		bizRoomService.save(record);
		return HttpResult.ok();
	}

    /**
     * 删除客房信息表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	@SystemControllerLog(description = "客房信息删除")
	public HttpResult delete(@RequestBody List<BizRoom> records) {
		bizRoomService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/page")
	@SystemControllerLog(description = "客房信息查询")
	public HttpResult findPage(BizRoomQuery simplePageReq) {
		return HttpResult.ok(bizRoomService.findPagePara(simplePageReq));
	}


	/**
	 * 上传宣传图片
	 * @return
	 */
	@RequestMapping(value="/uploadFile")
	@SystemControllerLog(description = "客房信息图片上传")
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
	@SystemControllerLog(description = "增减牌价信息")
	public HttpResult producePriceCalendar(@RequestBody BizProPrice bizProPrice) {
		Map map = new HashMap();
		map = bizRoomService.producePriceCalendar(bizProPrice);
		return HttpResult.ok(map);
	}

	/**
	 * 生成用户输入的牌价数据，根据输入的和之前的做对比
	 *
	 * @return
	 */
	@PostMapping(value="/priceDatePro")
	@SystemControllerLog(description = "牌价信息查询")
	public HttpResult priceDatePro(@RequestBody BizProPrice bizProPrice) {
		Map map = new HashMap();
		map = bizRoomService.productDatePrice(bizProPrice);
		return HttpResult.ok(map);
	}

	/**
	 * 查询出库存信息展示给客户
	 * @param
	 * @return
	 */
	@PostMapping(value="/produceStockCalendar")
	@SystemControllerLog(description = "增减库存信息")
	public HttpResult produceStockCalendar(@RequestBody BizProInv bizProInv) {
		Map map = new HashMap();
		map = bizRoomService.produceStockCalendar(bizProInv);
		return HttpResult.ok(map);
	}

	/**
	 * 生成用户输入的库存数据，根据输入的和之前的做对比
	 *
	 * @return
	 */
	@PostMapping(value="/stockDatePro")
	@SystemControllerLog(description = "库存信息查询")
	public HttpResult stockDatePro(@RequestBody BizProInv bizProInv) {
		Map map = new HashMap();
		map = bizRoomService.productDateStock(bizProInv);

		return HttpResult.ok(map);
	}

}
