package com.hotel.core.mybatis.mapper;

import com.hotel.common.entity.Entity;
import tk.mybatis.mapper.common.Mapper;


/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public interface AbstractMapper<T extends Entity> extends Mapper<T> {
}
