package com.don.oss.adapter;

import com.don.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 文件存储适配器
 *
 * @author A
 * @date 2025/8/20
 **/
public interface StorageAdapter {

    /**
     * 创建bucket
     */
    public void createBucket(String bucket);
    /**
     * 上传文件
     */
    public void uploadFile(MultipartFile multipartFile, String bucket, String objectName);

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket();

    /**
     * 列出所有桶及文件
     */
    public List<FileInfo> getAllFile(String bucket);

    /***
     * 下载文件
     * @param bucket
     * @param objectName
     * @return
     * @throws Exception
     */

    public InputStream downLoad(String bucket, String objectName);
    /**
     * 删除桶
     */
    public void deleteBucket(String bucket);
    /**
     * 删除文件
     */
    public void deleteObject(String bucket, String objectName);
}
