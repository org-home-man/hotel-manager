package com.hotel.admin.mapper;

import com.hotel.admin.model.BizAgreement;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface BizAgreementMapper extends AbstractMapper<BizAgreement> {

    List<BizAgreement> findPage();

}
