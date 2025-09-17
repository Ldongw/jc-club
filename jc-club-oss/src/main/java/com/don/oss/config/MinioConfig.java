package com.don.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * minio 配置管理
 *
 * @author A
 * @date 2025/8/20
 **/
@Configuration
@RefreshScope
public class MinioConfig {
    /**
     * minioUrl
     */
    @Value("${minio.url}")
    private String url;

    /**
     * minio账户
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 构造minioClient
     * @return
     */
    @Bean
    public MinioClient getMinioClient(){
        return MinioClient.builder().endpoint(url).credentials(accessKey,secretKey).build();
    }
}
