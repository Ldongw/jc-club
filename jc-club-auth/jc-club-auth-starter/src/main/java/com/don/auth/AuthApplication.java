package com.don.auth;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.don")
@MapperScan("com.don.**.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class AuthApplication {
    public static void main(String[] args) throws JSchException {
        sshEstablish();
        SpringApplication.run(AuthApplication.class, args);
    }

    private static void sshEstablish() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession("root", "129.204.53.142", 22);
        session.setPassword("@TXYfwq798798");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(3307, "127.0.0.1", 3306);
    }

}