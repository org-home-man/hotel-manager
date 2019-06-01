package com.hotel.admin.service.impl;

import com.hotel.admin.constants.Constant;
import com.hotel.admin.mapper.SysSocketMessageMapper;
import com.hotel.admin.model.SysSocketMessage;
import com.hotel.admin.service.ISysWebSocketMessageService;
import com.hotel.common.entity.auth.ISysUser;
import com.hotel.common.utils.Utils;
import com.hotel.core.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * websocket消息对象
 */
@Service
public class SysWebSocketMessageServiceImpl implements ISysWebSocketMessageService {
    @Autowired
    private SysSocketMessageMapper socketMessageMapper;

    @Override
    public void update(SysSocketMessage socketMessage) {
        if(Utils.isEmpty(socketMessage.getId())){
            return;
        }
        //点击后更新状态
        socketMessage.setStatus(Constant.BOOL_YES); //已查看
        socketMessageMapper.updateByPrimaryKeySelective(socketMessage);
    }

    @Override
    public List<SysSocketMessage> findAll() {
        SysSocketMessage sysSocketMessage = new SysSocketMessage();
        ISysUser currentUser = UserContext.getCurrentUser();
        sysSocketMessage.setUserId(currentUser.getId());
        List<SysSocketMessage> list = socketMessageMapper.selectNoRead(sysSocketMessage);
        return list;
    }

    @Override
    public Long findNoReadCount() {
        SysSocketMessage sysSocketMessage = new SysSocketMessage();
        ISysUser currentUser = UserContext.getCurrentUser();
        sysSocketMessage.setUserId(currentUser.getId());
        Long count = socketMessageMapper.selectNoReadCount(sysSocketMessage);
        return count;
    }
}
