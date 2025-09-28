package com.don.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author A
 * @date 2025/9/25
 **/
@SpringBootApplication
@ComponentScan("com.don")
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class);
    }

}
