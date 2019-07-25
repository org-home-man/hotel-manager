package com.hotel.admin.service;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.dto.BizHotlUpdate;
import com.hotel.admin.model.BizHotl;
import com.hotel.core.page.Page;
import com.hotel.core.service.CurdService;
import com.hotel.core.service.IService;

import java.util.List;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlService)         
 * ---------------------------
 */
public interface BizHotlService extends IService<BizHotl> {
//    BizHotl findByName(String username);
    List<BizHotl> findAllData(BizHotl bizHotl);
    Page findPage(BizHotelQueryDto dto);
    int delete(List<BizHotl> record);
    }
