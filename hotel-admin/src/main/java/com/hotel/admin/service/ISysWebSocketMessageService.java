package com.hotel.admin.service;

import com.hotel.admin.model.SysSocketMessage;

import java.util.List;
import java.util.Set;

public interface ISysWebSocketMessageService {

    /**
     * 保存消息对象
     * @param socketMessage
     */
    void save(SysSocketMessage socketMessage);

    /**
     * 更新消息对象状态
     * @param socketMessage
     */
    void update(SysSocketMessage socketMessage);

    /**
     * 通过当前登录用户查询当前未读消息集合
     * @return
     */
    Set<String> findAll();

    /**
     * 查询未读通知
     * @return
     */
    Long findNoReadCount();
}
