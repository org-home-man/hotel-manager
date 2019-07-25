package com.hotel.admin.service;

import com.hotel.admin.dto.ManagerRequestReportDto;
import com.hotel.admin.dto.UserRequestReportDto;
import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.model.BizPuchsExt;
import com.hotel.admin.model.BizPuchsExtDto;
import com.hotel.admin.model.BizPuchsUpdate;
import com.hotel.admin.qo.BizPuchsQuery;
import com.hotel.core.service.IService;
import com.hotel.core.service.NewCurdService;

import java.util.List;

/**
 * ---------------------------
 * 订单信息表 (BizPuchsService)         
 * ---------------------------
 */
public interface BizPuchsService extends IService<BizPuchs> {
    int updateInfo(BizPuchsUpdate record);

    int puchsConfirm(BizPuchsUpdate record);

    List<BizPuchsExtDto> findPage(BizPuchsQuery bizPuchsQuery);

    int orderCancel(List<BizPuchsExtDto> bizPuchs);

    void cancel(BizPuchsExtDto bizPuchs);

    int accountsConfirm(BizPuchsUpdate record);

    List<UserRequestReportDto> exportExcel(BizPuchsUpdate record);

    List<ManagerRequestReportDto> exportManagerExcel(BizPuchsUpdate record);
}
