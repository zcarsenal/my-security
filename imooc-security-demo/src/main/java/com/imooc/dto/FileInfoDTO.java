package com.imooc.dto;

public class FileInfoDTO {

    private String absoluteFilePath;

    public FileInfoDTO(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }

    public void setAbsoluteFilePath(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }
}
