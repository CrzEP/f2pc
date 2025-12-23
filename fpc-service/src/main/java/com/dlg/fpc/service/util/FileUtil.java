package com.dlg.fpc.service.util;

import com.dlg.fpc.service.exceptioon.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * 文件工具
 */
@Slf4j
public class FileUtil extends org.apache.commons.io.FileUtils {

    /**
     * 读取文件
     *
     * @param filePath 文件地址
     * @return 字符
     */
    public static String readFileToString(String filePath) {
        try {
            return readFileToString(new File(filePath), UTF_8.name());
        } catch (IOException e) {
            log.error("readFileToString", e);
            throw BusinessException.ex("文件读取失败： " + filePath);
        }
    }

    /**
     * 写入文件
     *
     * @param filePath 文件地址
     */
    public static void writeFileString(String filePath, String content) {
        try {
            writeStringToFile(new File(filePath), content, UTF_8.name());
        } catch (IOException e) {
            log.error("writeFileToString", e);
            throw BusinessException.ex("文件写入失败： " + filePath);
        }
    }

}
