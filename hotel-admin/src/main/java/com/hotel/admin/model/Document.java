package com.hotel.admin.model;

import com.hotel.common.entity.Entity;

import javax.persistence.Table;

@Table(name = "so_document")
public class Document extends Entity{

    private String releationId;

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

    public String getReleationId() {
        return releationId;
    }

    public void setReleationId(String releationId) {
        this.releationId = releationId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}