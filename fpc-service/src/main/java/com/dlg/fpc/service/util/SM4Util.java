package com.dlg.fpc.service.util;

import com.dlg.fpc.service.config.StaticInit;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * sm4算法工具
 */
public class SM4Util {

    // SM4算法名称
    private static final String ALGORITHM_NAME = "SM4";
    // SM4/ECB/PKCS5Padding算法名称
    private static final String ALGORITHM_ECB_PKCS5PADDING = "SM4/ECB/PKCS5Padding";

    /**
     * SM4加密
     *
     * @param plainText 待加密的字符串
     * @param key       16字符长度的密钥
     * @return Base64编码后的加密字符串
     * @throws Exception 加密过程中可能抛出的异常
     */
    public static String sm4Encrypt(String plainText, String key) throws Exception {
        // 将密钥转换为字节数组
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        // 创建密钥规范
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM_NAME);
        // 获取Cipher对象实例
        Cipher cipher = Cipher.getInstance(ALGORITHM_ECB_PKCS5PADDING, BouncyCastleProvider.PROVIDER_NAME);
        // 初始化Cipher为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 获取加密byte数组
        byte[] cipherBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        // 输出为Base64编码
        return new String(ECodeUtil.en64(cipherBytes), StandardCharsets.UTF_8);
    }

    /**
     * SM4解密
     *
     * @param cipherText Base64编码后的加密字符串
     * @param key        16字符长度的密钥
     * @return 解密后的字符串
     * @throws Exception 解密过程中可能抛出的异常
     */
    public static String sm4Decrypt(String cipherText, String key) throws Exception {
        // 将密钥转换为字节数组
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        // 创建密钥规范
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM_NAME);
        // 获取Cipher对象实例
        Cipher cipher = Cipher.getInstance(ALGORITHM_ECB_PKCS5PADDING, BouncyCastleProvider.PROVIDER_NAME);
        // 初始化Cipher为解密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // 获取加密byte数组
        byte[] cipherBytes = ECodeUtil.de64(cipherText.getBytes(StandardCharsets.UTF_8));
        // 解密得到原文
        byte[] plainBytes = cipher.doFinal(cipherBytes);
        // 输出为字符串
        return new String(plainBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        StaticInit.BCInit();
        try {
            // 示例密钥，实际使用中应替换为数据中台提供的密钥
            String appSecret = "1234567890abcdef"; // 16字符长度的密钥
            String secretKey = "1234567890abcdef"; // 16字符长度的密钥

            // 待加密的字符串
            String plainText = "Hello, world!";

            // 加密
            String encryptedText = sm4Encrypt(plainText, appSecret);
            System.out.println("加密后（Base64编码）：" + encryptedText);

            // 解密
            String decryptedText = sm4Decrypt(encryptedText, secretKey);
            System.out.println("解密后：" + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
