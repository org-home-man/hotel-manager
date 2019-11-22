package com.hotel.admin.service.impl;

import com.hotel.admin.mapper.BizAgreementMapper;
import com.hotel.admin.model.BizAgreement;
import com.hotel.admin.service.BizAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizAgreementServiceImpl implements BizAgreementService {

    @Autowired
    BizAgreementMapper bizAgreementMapper;

    @Override
    public List<BizAgreement> findPage(BizAgreement bizAgreement) {

        List<BizAgreement> list = bizAgreementMapper.findPage();

        return list;
    }
}
