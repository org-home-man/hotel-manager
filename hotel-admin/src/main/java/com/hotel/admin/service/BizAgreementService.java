package com.hotel.admin.service;

import com.hotel.admin.model.BizAgreement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BizAgreementService {

    List<BizAgreement> findPage(BizAgreement bizAgreement);
}
