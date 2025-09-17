package com.don.oss.adapter;

import com.don.oss.entity.FileInfo;
import com.don.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author A
 * @date 2025/8/20
 **/
public class AliStorageAdapter implements StorageAdapter {

    @Autowired
    private MinioUtil minioUtil;
    @Override
    public void createBucket(String bucket) {

    }

    @Override
    public void uploadFile(MultipartFile multipartFile, String bucket, String objectName) {

    }

    @Override
    public List<String> getAllBucket() {
        List<String> bucketNameList = new ArrayList<>();
        bucketNameList.add("aliyun");
        return bucketNameList;
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFile(String bucket) {
        return minioUtil.getAllFile(bucket);
    }

    @Override
    public InputStream downLoad(String bucket, String objectName) {
        return null;
    }

    @Override
    public void deleteBucket(String bucket) {

    }

    @Override
    public void deleteObject(String bucket, String objectName) {

    }
}
