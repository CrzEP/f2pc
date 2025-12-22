package com.dlg.fpc.service.util;

import com.dlg.fpc.service.exceptioon.bussiness.EnCodeException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * sm2非对称加密算法 工具类
 */
public class SM2Util {

    static  {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * sm2 encrypt
     *
     * @param plainText 原文
     * @param publicKey 公钥
     * @return 秘文
     */
    public static String encrypt(String plainText, PublicKey publicKey) {
        byte[] encryptedData;
        try {
            byte[] bytes = ECodeUtil.en64(plainText).getBytes(StandardCharsets.UTF_8);
            Cipher cipher = Cipher.getInstance("SM2");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedData = cipher.doFinal(bytes);
        }catch (Exception e) {
            throw new EnCodeException("sm2 encode error");
        }
        return new String(ECodeUtil.en64(encryptedData),StandardCharsets.UTF_8);
    }

    /**
     * sm2 decrypt
     *
     * @param cipherText 秘文
     * @param privateKey 私钥
     * @return 原文
     */
    public static String decrypt(String cipherText,PrivateKey privateKey){
        byte[] de64 = ECodeUtil.de64(cipherText.getBytes(StandardCharsets.UTF_8));
        byte[] decryptedData;
        try {
            Cipher cipher = Cipher.getInstance("SM2");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedData = cipher.doFinal(de64);
            return ECodeUtil.de64(new String(decryptedData,StandardCharsets.UTF_8));
        }catch (Exception e) {
            e.printStackTrace();
            throw new EnCodeException("sm2 decode error");
        }
    }

    public static void main(String[] args) throws Exception {
        // 使用sm2p256v1曲线生成密钥对
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGen.initialize(new ECGenParameterSpec("sm2p256v1"));
        KeyPair keyPair = keyPairGen.generateKeyPair();

        System.out.println("prKey : " + keyPair.getPrivate());
        System.out.println("puKey : " + keyPair.getPublic());

        String data = "123123123";
        System.out.println("sour: "+data);
        String scdata = encrypt(data,keyPair.getPublic());
        System.out.println("sec: "+scdata);
        String scdata1 = decrypt(scdata,keyPair.getPrivate());
        System.out.println("sour2: "+ scdata1 );

    }

}
