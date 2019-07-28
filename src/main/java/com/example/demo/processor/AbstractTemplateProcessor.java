package com.example.demo.processor;

import com.example.demo.entity.CertType;
import com.example.demo.entity.ParamList;
import com.example.demo.entity.Response;
import org.springframework.beans.factory.InitializingBean;

import java.util.Set;

/**
 * 抽象模板，定义所有 processor 的处理逻辑
 * @author fanpengyi
 * @version 1.0
 * @date 2019-7-28
 */
public abstract class AbstractTemplateProcessor<T> implements DataSupplier<T>,InitializingBean{

    private Set<CertType> types;

    public AbstractTemplateProcessor() {
        this.types = types;
    }

    /**
     * 抽象模板的执行方法
     * @param paramList
     * @param types
     */
    public Response<Void> process(ParamList paramList, Set<CertType> types){
        //获取参数
        T supply = supply(paramList);
        //process的判断
        if(accept(supply,types)){
            //真正执行 process
            return doProcess(paramList.getUseId());

        }
        return Response.success();
    }

    /**
     * 判断 process 执行方法的前置判断
     * @param declPort
     * @param types
     * @return
     */
    protected abstract boolean accept(T declPort,Set<CertType> types);

    /**
     * 真正执行 process 方法
     * @param useId
     */
    protected abstract Response<Void> doProcess(String useId);

    /**
     * 获取每个 processor 支持的类型
     * @return
     */
    public abstract Set<CertType> getTypes();

    /**
     * 加载类的后置处理
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception{
        TemplateProcessorMananger.getInstance().register(this);

    }

}
