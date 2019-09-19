package com.hotel.core.service;

import com.hotel.common.entity.Entity;
import com.hotel.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public class BaseService<T extends Entity> implements IService<T> {
    @Autowired
    Mapper<T> mapper;

    public BaseService() {
    }

    public T selectByKey(Object key) {
        if(Utils.isEmpty(key)){
            return null;
        }
        return this.mapper.selectByPrimaryKey(key);
    }

    public List<T> select(T entity) {
        return this.mapper.select(entity);
    }

    public int save(T entity) {
        return this.mapper.insertSelective(entity);
    }

    public int save(Collection<T> entities) {
        if (Utils.isEmpty(entities)) {
            return 0;
        } else {
            Iterator<T> var2 = entities.iterator();

            while(var2.hasNext()) {
                T t = var2.next();
                this.save(t);
            }

            return entities.size();
        }
    }

    public int delete(Object key) {
        return this.mapper.deleteByPrimaryKey(key);
    }

    public int delete(Collection<T> keys) {
        if (Utils.isEmpty(keys)) {
            return 0;
        } else {
            Iterator<T> var2 = keys.iterator();

            while(var2.hasNext()) {
                T obj = var2.next();
                this.delete(obj.getId());
            }

            return keys.size();
        }
    }

    public int updateAll(T entity) {
        return this.mapper.updateByPrimaryKey(entity);
    }

    public int updateNotNull(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity);
    }

    public int updateAll(Collection<T> entities) {
        if (Utils.isEmpty(entities)) {
            return 0;
        } else {
            Iterator var2 = entities.iterator();

            while(var2.hasNext()) {
                T entity = (T)var2.next();
                this.updateAll(entity);
            }

            return entities.size();
        }
    }

    public int updateNotNull(Collection<T> entities) {
        if (Utils.isEmpty(entities)) {
            return 0;
        } else {
            Iterator var2 = entities.iterator();

            while(var2.hasNext()) {
                T entity = (T)var2.next();
                this.updateNotNull(entity);
            }

            return entities.size();
        }
    }

    public List<T> selectAll() {
        return this.mapper.selectAll();
    }

}
