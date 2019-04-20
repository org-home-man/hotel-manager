package com.hotel.core.service;

import com.hotel.common.entity.Entity;

import java.util.Collection;
import java.util.List;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public interface IService<T extends Entity> {
    T selectByKey(Object var1);

    List<T> selectAll();

    List<T> select(T var1);

    int save(T var1);

    int save(Collection<T> var1);

    int delete(Object var1);

    int delete(Collection<Object> var1);

    int updateAll(T var1);

    int updateNotNull(T var1);

    int updateAll(Collection<T> var1);

    int updateNotNull(Collection<T> var1);
}

