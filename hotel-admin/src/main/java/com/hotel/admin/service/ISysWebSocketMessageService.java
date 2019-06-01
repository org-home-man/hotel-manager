package com.hotel.admin.service;

import com.hotel.admin.model.SysSocketMessage;

import java.util.List;

public interface ISysWebSocketMessageService {

    /**
     * 更新消息对象状态
     * @param socketMessage
     */
    void update(SysSocketMessage socketMessage);

    /**
     * 通过当前登录用户查询当前未读消息集合
     * @return
     */
    List<SysSocketMessage> findAll();

    /**
     * 查询未读通知
     * @return
     */
    Long findNoReadCount();
}
