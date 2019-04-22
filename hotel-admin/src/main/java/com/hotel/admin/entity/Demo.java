package com.hotel.admin.entity;

import com.hotel.common.entity.Entity;

public class Demo extends Entity {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;


    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}