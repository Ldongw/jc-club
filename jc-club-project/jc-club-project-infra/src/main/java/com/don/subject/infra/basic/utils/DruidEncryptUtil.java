package com.don.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * 数据库加密
 * @author A
 * @date 2025/8/11
 **/
public class DruidEncryptUtil {
    private static String publicKey;
    private static String privateKey;
    static {
        try{
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            publicKey = keyPair[1];
            System.out.println("private: " + privateKey);
            System.out.println("public: " + publicKey);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();;
        }catch (NoSuchProviderException e){
            e.printStackTrace();
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypt = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("encrypt: " + encrypt);
        return encrypt;
    }

    public static String decrypt(String encryptText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptText);
        System.out.println("encrypt: " + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception {
        encrypt("@TXYfwq798798");
    }
}
