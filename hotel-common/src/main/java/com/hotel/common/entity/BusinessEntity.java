package com.hotel.common.entity;

/**
 * @Author:chenchao
 * @Version: v1.0.0
 * @Despriction:  业务公共实体类
 * @Date:Created in 2019-05-05
 */
public class BusinessEntity extends Entity{

    public static final long serialVersionUID = 6568512953945826557L;

    private Long createId;
    private String createName;
    private String createTime;
    private Long updateId;
    private String updateName;
    private String updateTime;

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
