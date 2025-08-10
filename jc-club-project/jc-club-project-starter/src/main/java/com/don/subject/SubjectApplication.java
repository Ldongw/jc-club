package com.don.subject;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication(scanBasePackages = "com.don")
@MapperScan("com.don.subject.infra.basic.mapper")
public class SubjectApplication {
    public static void main(String[] args) throws JSchException {
        sshEstablish();
        SpringApplication.run(SubjectApplication.class, args);
    }

    private static void sshEstablish() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession("ubuntu", "175.178.244.96", 22);
        session.setPassword("@TXYfwq798798");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        session.setPortForwardingL(3307, "127.0.0.1", 3306);
    }

}