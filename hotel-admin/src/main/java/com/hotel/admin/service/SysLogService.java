package com.hotel.admin.service;

import com.hotel.admin.model.SysLog;
import com.hotel.core.page.Page;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.IService;

/**
 * 日志管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysLogService extends IService<SysLog> {
    /**
     * 分页查询数据
     * @param name
     * @return
     */
    Page findPage(String name);
}
