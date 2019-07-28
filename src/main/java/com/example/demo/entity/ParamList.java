package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数列表
 * @author fanpengyi
 * @version 1.0
 * @date 2019-7-28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamList {
    /**
     * 使用的参数
     */
    private String useId;

    /**
     * 返回的结果
     */
    private Map<String,Object> resultMaps = new HashMap<>();

}
