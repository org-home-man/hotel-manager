package com.hotel.admin.entity;

import com.hotel.common.bean.ExcelColumn;
import com.hotel.common.entity.BusinessEntity;
import com.hotel.common.entity.Entity;

public class Demo extends Entity {


    /**
     * 
     */
    @ExcelColumn(value = "姓名",col = 1)
    private String name;
    @ExcelColumn(value = "性别",col = 2)
    private String sex;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}