package com.hotel.admin.mapper;

import com.hotel.admin.dto.BizHotelQueryDto;
import com.hotel.admin.dto.BizHotlUpdate;
import com.hotel.admin.model.BizHotl;
import com.hotel.core.mybatis.mapper.AbstractMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ---------------------------
 * 酒店信息表 (BizHotlMapper)         
 * ---------------------------
 */
public interface BizHotlMapper extends AbstractMapper<BizHotl>{


    List<Map> findPageByPara(BizHotelQueryDto dto);
    int insertInfo(BizHotl record);


        /**
         * 基础分页查询
         * @param record
         * @return
         */
    List<BizHotl> findPage();
    int deleteUp(BizHotl record);
    int update(BizHotl record);
    BizHotl findById(BizHotl record);

    String selectMaxSequence();
}