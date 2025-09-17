package com.don.oss.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.don.oss.adapter.StorageAdapter;
import com.don.oss.adapter.AliStorageAdapter;
import com.don.oss.adapter.MinioStorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author A
 * @date 2025/8/21
 **/
@Configuration
@RefreshScope
public class StorageConfig {

    @Value(value = "${storage.service.type}")
    private String storageType;

    @Bean
    @RefreshScope
//    @Lazy
    public StorageAdapter storageService() throws IllegalAccessException {
        if ("minio".equals(storageType)){
            return new MinioStorageAdapter();
        } else if ("aliyun".equals(storageType)) {
            return new AliStorageAdapter();
        }else {
            throw new IllegalAccessException("未找到对应文件存储处理器");
        }
    }

}
