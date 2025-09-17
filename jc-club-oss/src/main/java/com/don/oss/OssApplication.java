package com.don.oss;


import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * oss 服务启动器
 */
@SpringBootApplication(scanBasePackages = "com.don")
@ComponentScan("com.don")
public class OssApplication {
    public static void main(String[] args) throws Exception{
//        sshEstablish();
//        sshNacos();
        SpringApplication.run(OssApplication.class);
    }

    private static void sshEstablish() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession("root", "129.204.53.142", 22);
        session.setPassword("@TXYfwq798798");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(9001, "127.0.0.1", 9000);
    }


}