package com.example.demo.processor;

import com.example.demo.entity.CertType;
import com.example.demo.entity.ParamList;
import com.example.demo.entity.Response;
import org.springframework.beans.factory.InitializingBean;

import java.util.Set;

/**
 * 抽象模板，定义所有 processor 的处理逻辑
 *
 * @author fanpengyi
 * @version 1.0
 * @date 2019-7-28
 */
public abstract class AbstractTemplateProcessor<T> implements DataSupplier<T>, InitializingBean {

    //所有的商品类型
    private Set<CertType> types;

    public AbstractTemplateProcessor() {
        this.types = getTypes();
    }

    /**
     * 抽象模板的执行方法
     *
     * @param paramList 业务层传过来的需要的​参数
     * @param types     业务层传过来d额商品类型
     */
    public Response<Void> process(ParamList paramList, Set<CertType> types) {
        //获取参数
        T supply = supply(paramList);
        //process的判断
        if (accept(supply, types)) {
            //真正执行 process
            return doProcess(paramList.getUseId());
        }
        return Response.success();
    }

    /**
     * 判断 process 执行方法的前置判断
     *
     * @param projectCode 商品编号
     * @param types       商品类型​
     * @return
     */
    protected abstract boolean accept(T projectCode​, Set<CertType> types);

    /**
     * 真正执行 process 方法
     *
     * @param bizId​
     */
    protected abstract Response<Void> doProcess(String biz​Id);

    /**
     * 获取每个 processor 支持的类型
     *
     * @return
     */
    public abstract Set<CertType> getTypes();

    /**
     * 加载类的后置处理，将processor注册到​处理器中
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        TemplateProcessorMananger.getInstance().register(this);

    }

}
