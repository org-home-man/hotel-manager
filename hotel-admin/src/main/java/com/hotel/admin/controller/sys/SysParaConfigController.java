package com.hotel.admin.controller.sys;

import com.hotel.admin.model.SysParaConfig;
import com.hotel.admin.service.SysParaConfigService;
import com.hotel.core.http.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 参数配置表 (SysParaConfigController)
 * ---------------------------
 */
@RestController
@RequestMapping("sysParaConfig")
public class SysParaConfigController {

	@Autowired
	private SysParaConfigService sysParaConfigService;

	/**
	 * 保存参数配置表
	 * @param record
	 * @return
	 */
	@PostMapping(value="/save")
	public HttpResult save(SysParaConfig record) {
		sysParaConfigService.save(record);
		return HttpResult.ok();
	}

    /**
     * 删除参数配置表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	public HttpResult delete(List<SysParaConfig> records) {
		sysParaConfigService.delete(records);
		return HttpResult.ok();
	}

    /**
     * 基础分页查询
     * @return
     */
	@PostMapping(value="/findPage")
	public HttpResult findPage() {
		return HttpResult.ok(sysParaConfigService.findPage());
	}

    /**
     * 根据主键查询
     * @param paraSubCode2
     * @return
     */
	@GetMapping(value="/findById")
	public HttpResult findById(String paraCode) {
		return HttpResult.ok(sysParaConfigService.selectByKey(paraCode));
	}

	/**
	 * 根据主键查询
	 * @param SysParaConfig
	 * @return
	 */
	@PostMapping(value="/findListData")
	public HttpResult findKeyValue(SysParaConfig record) {

		return HttpResult.ok(sysParaConfigService.findKeyValue(record));
	}
	@PostMapping(value="/findByCode")
	public HttpResult findByCode(SysParaConfig record) {

		return HttpResult.ok(sysParaConfigService.findByCode(record));
	}

	@PostMapping(value="/findListDataHotel")
	public HttpResult findKeyValueHotel(SysParaConfig record) {

		return HttpResult.ok(sysParaConfigService.findKeyValueHotel(record));
	}

	@PostMapping(value="/findListDataHotelRoom")
	public HttpResult findKeyValueHotelRoom(SysParaConfig record) {

		return HttpResult.ok(sysParaConfigService.findKeyValueHotelRoom(record));
	}
}
