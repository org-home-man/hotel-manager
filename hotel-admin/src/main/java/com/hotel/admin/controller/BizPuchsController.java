package com.hotel.admin.controller;

import com.hotel.admin.dto.ManagerRequestReportDto;
import com.hotel.admin.dto.UserRequestReportDto;
import com.hotel.admin.dto.WrDetailDto;
import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.model.BizPuchsExtDto;
import com.hotel.admin.model.BizPuchsUpdate;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.admin.service.BizPuchsService;
import com.hotel.admin.util.ExcelUtils;
import com.hotel.core.annotation.SystemControllerLog;
import com.hotel.core.context.PageContext;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import com.hotel.core.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 订单信息表 (BizPuchsController)         
 * ---------------------------
 */
@RestController
@RequestMapping("bizPuchs")
public class BizPuchsController {

	@Autowired
	private BizPuchsService bizPuchsService;

	/**
	 * 保存订单信息表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	@SystemControllerLog(description = "订单保存/编辑")
	public HttpResult save(BizPuchs record) {
		bizPuchsService.save(record);
		return HttpResult.ok();
	}

	/**
	 * 保存订单信息表
	 * @param record
	 * @return
	 */
	@PostMapping(value="/update")
	@SystemControllerLog(description = "订单更新")
	public HttpResult update(BizPuchsUpdate record) {
		System.out.println("licy");
		bizPuchsService.updateInfo(record);
		return HttpResult.ok();
	}

	/**
	 * 保存订单信息表
	 * @param record
	 * @return
	 */
	@PostMapping(value="/confirm")
	@SystemControllerLog(description = "订单提交")
	public HttpResult confirm( BizPuchsUpdate record) {
		bizPuchsService.puchsConfirm(record);
		return HttpResult.ok();
	}

		/**
         * 删除订单信息表
         * @param records
         * @return
         */
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<BizPuchs> records) {
		bizPuchsService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @param bizPuchsQuery
     * @return
     */    
	@PostMapping(value="/findPage")
	@SystemControllerLog(description = "订单查询")
	public Page findPage(BizPuchsQuery bizPuchsQuery) {
		bizPuchsService.findPage(bizPuchsQuery);
		return PageContext.getPage();
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	public HttpResult findById(Long id) {
		return HttpResult.ok(bizPuchsService.selectByKey(id));
	}

	@PostMapping(value = "orderCancel")
	@SystemControllerLog(description = "订单取消")
	public HttpResult OrderCancel(BizPuchsExtDto bizPuchs){
        bizPuchsService.cancel(bizPuchs);
		return HttpResult.ok();
	}


	/**
	 * 订单信息更新 订单状态（结算）
	 *  @param record
	 * @return
	 */
	@PostMapping(value="/accountsConfirm")
	@SystemControllerLog(description = "订单结算")
	public HttpResult accountsConfirm( BizPuchsUpdate record) {
		bizPuchsService.accountsConfirm(record);
		return HttpResult.ok();
	}

	/**
	 * 导出 订单状态excel
	 *  @param record
	 * @return
	 */
	@RequestMapping(value="/exportExcel")
	@SystemControllerLog(description = "用户订单信息导出")
	public void exportExcel(HttpServletResponse response, BizPuchsUpdate record) {
		List<UserRequestReportDto> li = bizPuchsService.exportExcel(record);
		Map<String,List<UserRequestReportDto>> map = new HashMap<>();
		map.put("userOrderReport",li);
		ExcelUtils.writeMoreSheetExcel(response,map,UserRequestReportDto.class,"userOrderReport");
	}

	/**
	 * 导出 订单管理员excel
	 *  @param record
	 * @return
	 */
	@RequestMapping(value="/exportManagerExcel")
	@SystemControllerLog(description = "管理员订单信息导出")
	public void exportManagerExcel(HttpServletResponse response, BizPuchsUpdate record) {
		List<ManagerRequestReportDto> li = bizPuchsService.exportManagerExcel(record);
		Map<String,List<ManagerRequestReportDto>> map = new HashMap<>();
		map.put("managerOrderReport",li);
		ExcelUtils.writeMoreSheetExcel(response,map,ManagerRequestReportDto.class,"managerOrderReport");
	}




}
