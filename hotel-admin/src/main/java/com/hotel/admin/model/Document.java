package com.hotel.admin.model;

import com.hotel.common.entity.Entity;

public class Document extends Entity{

    private Long releationId;

    private String name;

    private Long size;

    private String storePath;

    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }



    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath == null ? null : storePath.trim();
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getReleationId() {
        return releationId;
    }

    public void setReleationId(Long releationId) {
        this.releationId = releationId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}