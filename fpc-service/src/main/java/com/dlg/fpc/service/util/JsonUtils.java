package com.dlg.fpc.service.util;

import com.dlg.fpc.service.exceptioon.BizException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lingui
 * @Date 2022-09-15 16:38:21
 */
@Slf4j
public class JsonUtils {

    /**
     * 转json
     * @param object 对象参数
     * @return 字符
     */
    public static String toJson(Object object){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        }catch (Exception e){
            log.error("gson 转换异常");
            throw new BizException("toJson error");
        }
    }

    public static <T> T toClass(String jsonstr,Class<T> clazz){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonstr, clazz);
        }catch (Exception e){
            log.error("gson 转换json对象异常");
            throw new BizException("toClass error");
        }
    }
}