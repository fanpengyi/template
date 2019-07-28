package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CertType {
    /**
     *
     */
    TYPE_1("1","第一描述"),
    /**
     *
     */
    TYPE_2("2","第二描述"),
    /**
     *
     */
    TYPE_3("3","第三描述");


    private String code;

    private String commemt;

    private static Map<String,CertType> constantMap;

    static{
        constantMap = new HashMap<>(CertType.values().length);
        for (CertType certType : CertType.values()) {
            constantMap.put(certType.getCode(),certType);
        }
    }

    public static CertType of(String code){ return constantMap.getOrDefault(code,null);}



}
