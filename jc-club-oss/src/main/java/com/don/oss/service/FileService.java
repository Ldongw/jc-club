package com.don.oss.service;

import com.don.oss.adapter.StorageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author A
 * @date 2025/8/21
 **/
@Service
public class FileService {

    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter){
        this.storageAdapter = storageAdapter;
    }

    /**
     * 列出所有桶
     */
    public List<String> getAllBucket(){
        return storageAdapter.getAllBucket();
    }

    public String getUrl(String bucketName, String objectName) {
        return this.storageAdapter.getUrl(bucketName, objectName);
    }

    public String uploadFile(MultipartFile uploadFile, String bucketName, String objectName) {
        storageAdapter.uploadFile(uploadFile, bucketName, objectName);
        objectName = objectName + "/" + uploadFile.getOriginalFilename();
        return storageAdapter.getUrl(bucketName, objectName);
    }
}
