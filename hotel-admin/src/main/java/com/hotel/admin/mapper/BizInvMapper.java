package com.hotel.admin.mapper;

import java.util.List;

import com.hotel.admin.model.BizInv;
import com.hotel.admin.model.BizPuchs;
import com.hotel.admin.model.SysUser;
import com.hotel.core.mybatis.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

/**
 * ---------------------------
 * 客房库存表 (BizInvMapper)         
 * ---------------------------
 */
public interface BizInvMapper  extends AbstractMapper<BizInv> {

    /**
     * 根据主键查询
     * @param roomCode
     * @return
     */    
    BizInv findByRoomCode(BizInv roomCode);

    /**
     * 基础分页查询
     * @return
     */    
    List<BizInv> findPage();

    List<BizInv> queryById(String roomCode);

    /*
   merge 更新插入数据库
  */
    int addByUser(BizInv code);

    int updateByUser(BizInv code);

    List<BizInv> findCancelBizInv(@Param("roomCode") String roomCode, @Param("inDate") String inDate, @Param("outDate") String outDate);

    Integer selectInventory(BizPuchs bizPuchs);
}