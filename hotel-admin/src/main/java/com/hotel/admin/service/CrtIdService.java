package com.hotel.admin.service;

import com.hotel.admin.model.CrtId;
import com.hotel.core.service.CurdService;
/**
 * ---------------------------
 * 自增序列表 (CrtIdService)         
 * ---------------------------
 */
public interface CrtIdService extends CurdService<CrtId> {
    CrtId findById(String crttype);
}
