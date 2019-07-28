package com.example.demo.processor;

import com.example.demo.entity.ParamList;

/**
 * 用于向抽象模板中传递需要的参数
 * @author fanpengyi
 * @version 1.0
 * @date 2019-7-28
 */
public interface DataSupplier<T> {

    /**
     * 根据 paramList 获取每个 processor 需要处理对象
     * @param paramList 参数列表
     * @return
     */
    T supply(ParamList paramList);
}
