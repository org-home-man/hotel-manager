package com.hotel.admin.entity;

import com.hotel.common.entity.BusinessEntity;

public class Demo extends BusinessEntity {


    /**
     * 
     */
    private String name;


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