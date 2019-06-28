package com.hotel.admin.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisHeartBeatSchedule {
    @Autowired
    RedisTemplate<Serializable, Serializable> redisTemplate;
    @Scheduled(cron = "0/10 * * * * *")
    public void timer() {
        redisTemplate.opsForValue().get("heartbeat");
    }

}
