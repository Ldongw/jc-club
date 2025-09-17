package com.don.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.don.oss.adapter.StorageAdapter;
import com.don.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author A
 * @date 2025/8/20
 **/
@RestController
@RefreshScope
public class FileController {

    @Resource
    private FileService fileService;

    @Value(value = "${storage.service.type}")
    private String storageType;

    @RequestMapping("/testGetAllBuckets")
    public String testGetAllBuckets() throws Exception{
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    @RequestMapping("/testNacos")
    public String testNacos() throws Exception{
        return storageType;
    }

}
