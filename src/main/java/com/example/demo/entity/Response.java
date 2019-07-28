package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 方法返回值
 */
@Getter
@Setter
@AllArgsConstructor
public class Response<T> {


    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;


    private int status;

    private String message;

    private T data;

    /**
     * 下面的泛型中 <T> 与后面的 Response<T> 中的 T没有关系
     * 方法前面的 T 是给这个方法级别指定泛型
     * 如果不加 前面的 T ，那么方法返回值只能返回Response<T>中T的类型
     * 加了前面的T，就可以随便返回 不受限制
     * @param status
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> message(int status,String message,T data){
        return new Response<>(status,message,data);
    }

    public static <T> Response<T> success(){
        return message(SUCCESS,"",null);

    }

    public static <T> Response<T> success(T data){
        return message(SUCCESS,"",data);

    }
}
