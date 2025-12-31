package com.dlg.fpc.service.util;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 属性对象复制工具
 */
public class BeanCopyUtils {

    /**
     * 复制回调接口
     *
     * @param <S>
     * @param <T>
     */
    public interface CallBack<S, T> {

        /**
         * 回调方法
         *
         * @param src    源对象
         * @param target 目的对象
         */
        void callBack(S src, T target);

    }

    /**
     * 浅拷贝单个对象：基于Spring BeanUtils实现
     *
     * @param source    源对象（非null）
     * @param targetCls 目标对象Class
     * @return 拷贝后的目标对象
     */
    @SneakyThrows
    public static <S, T> T copy(S source, Class<T> targetCls) {
        if (Objects.isNull(source)) {
            return null;
        }
        // 反射创建目标对象实例
        T target = targetCls.newInstance();
        // 核心：复制属性（浅拷贝）
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * 浅拷贝集合：批量处理对象拷贝
     *
     * @param source    源对象集合（非null）
     * @param targetCls 目标对象Class
     * @return 拷贝后的目标对象集合
     */
    @SneakyThrows
    public static <S, T> List<T> listCopy(Collection<S> source, Class<T> targetCls) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(source.size());
        source.forEach(s -> list.add(copy(s, targetCls)));
        return list;
    }

    /**
     * 浅拷贝集合：批量处理对象拷贝 附带处理回调函数
     *
     * @param source    源集合
     * @param targetCls 目的对象
     * @param callBack  回调函数
     * @param <S>       源对象类型
     * @param <T>       目的对象类型
     * @return 新集合
     */
    public static <S, T> List<T> listCopy(Collection<S> source, Class<T> targetCls, CallBack<S, T> callBack) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(source.size());
        for (S s : source) {
            T t = copy(s, targetCls);
            callBack.callBack(s, t);
            list.add(t);
        }
        return list;
    }

}
