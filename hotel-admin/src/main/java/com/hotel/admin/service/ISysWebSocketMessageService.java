package com.hotel.admin.service;

import com.hotel.admin.model.SysSocketMessage;

import java.util.List;

public interface ISysWebSocketMessageService {

    void update(SysSocketMessage socketMessage);

    List<SysSocketMessage> findAll();
}
