package com.don.club.gateway;


import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 网关启动器
 */
@SpringBootApplication(scanBasePackages = "com.don")
@ComponentScan("com.don")
public class GatewayApplication {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(GatewayApplication.class);
    }
}