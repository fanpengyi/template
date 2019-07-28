package com.example.demo.intercept;

import com.example.demo.entity.ParamList;

/**
 * 每个线程私有的变量，类似于全局变量，但是每个线程自己有自己的一份
 * 放在 拦截器中初始化 和 销毁,有初始化，有销毁，不然会有内存泄漏的风险
 *
 * @author fanpengyi
 * @version 1.0
 * @date 2019-7-28
 */
public class DataHolder {

    private static ThreadLocal<ParamList> threadLocal;

    /**
     * 初始化 theadLocal
     */
    public static void start(){

        if(threadLocal == null){
            threadLocal = new ThreadLocal<>();
        }

        if(threadLocal.get() == null){
            threadLocal.set(new ParamList());
        }

    }

    public static ParamList get(){return threadLocal.get();}

    public static void update(ParamList paramList){threadLocal.set(paramList);}

    public static void shutdown(){
        if(threadLocal != null){
            threadLocal.remove();
        }
    }

}
