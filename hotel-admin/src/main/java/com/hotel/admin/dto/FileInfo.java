package com.hotel.admin.dto;

/**
 * 描述:
 * 作者:cc
 * 时间:2018-09-20 上午10:06
 */
public class FileInfo {

    private String fileName;

    private Long fileSize;

    private byte[] content;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
