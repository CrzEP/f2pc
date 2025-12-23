package com.dlg.fpc.service.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 编码工具/统一处理
 */
public class ECodeUtil {

    public static String en64(String data) {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        return new String(en64(bytes), StandardCharsets.UTF_8);
    }

    public static byte[] en64(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    public static String en64ToStr(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes),StandardCharsets.UTF_8);
    }


    public static String de64(String data) {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        return new String(de64(bytes), StandardCharsets.UTF_8);
    }

    public static byte[] de64(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }

    public static String de64ToStr(byte[] bytes) {
        return new String(Base64.getDecoder().decode(bytes),StandardCharsets.UTF_8);
    }

    public static byte[] de64ToByte(String bytes) {
        return Base64.getDecoder().decode(bytes);
    }

}
