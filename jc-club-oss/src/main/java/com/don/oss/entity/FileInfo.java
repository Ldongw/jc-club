package com.don.oss.entity;

/**
 * 文件类
 *
 * @author A
 * @date 2025/8/20
 **/
public class FileInfo {

    private String fileName;

    private Boolean directoryFlag;

    private String etag;

    public FileInfo(String fileName) {
        this.fileName = fileName;
    }

    public FileInfo() {
    }

    public FileInfo(String fileName, Boolean directoryFlag, String etag) {
        this.fileName = fileName;
        this.directoryFlag = directoryFlag;
        this.etag = etag;
    }

    /**
     * 获取
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取
     * @return directoryFlag
     */
    public Boolean getDirectoryFlag() {
        return directoryFlag;
    }

    /**
     * 设置
     * @param directoryFlag
     */
    public void setDirectoryFlag(Boolean directoryFlag) {
        this.directoryFlag = directoryFlag;
    }

    /**
     * 获取
     * @return etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     * 设置
     * @param etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String toString() {
        return "FileInfo{fileName = " + fileName + ", directoryFlag = " + directoryFlag + ", etag = " + etag + "}";
    }
}
