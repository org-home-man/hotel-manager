package com.hotel.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:
 * @Date:Created in 2019-04-20
 */
public class Entity implements Serializable {
    private static final long serialVersionUID = 2716244744215225664L;
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    public Entity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

