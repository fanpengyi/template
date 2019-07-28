package com.example.demo.processor;

import com.example.demo.entity.CertType;
import com.example.demo.entity.ParamList;
import com.example.demo.entity.Response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * process 管理器 用于注册 process 与 执行所有的 process
 * @author fanpengyi
 * @version 1.0
 * @date 2019-7-28
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateProcessorMananger {

    /**
     *静态的引用，单例
     */
    @Getter
    private static TemplateProcessorMananger Instance = new TemplateProcessorMananger();

    //注册管理器，统一放置 process，如果有顺序 需要注意顺序
    private List<AbstractTemplateProcessor> processors = new LinkedList<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * 注册方法
     */
    public void register(AbstractTemplateProcessor processor){

        try{
            //加锁是保证安全，不然可能报错 CopyOnWriteList 是线程安全的
            lock.writeLock().lock();
            processors.add(processor);
        }finally{
            lock.writeLock().unlock();
        }
    }



    public List<Response<Void>> process(ParamList paramList, Set<CertType> types){

        try{
            //加锁是为了在读的时候不允许其他线程操作 processors
            lock.readLock().lock();
            return processors.stream()
                    .map( o-> (Response<Void>)o.process(paramList,types))
                    .collect(Collectors.toList());
        }finally{
            lock.readLock().unlock();
        }



    }


}
