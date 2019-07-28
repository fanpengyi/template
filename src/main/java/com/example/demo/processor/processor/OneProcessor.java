package com.example.demo.processor.processor;

import com.example.demo.entity.CertType;
import com.example.demo.entity.ParamList;
import com.example.demo.entity.Response;
import com.example.demo.intercept.DataHolder;
import com.example.demo.processor.AbstractTemplateProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class OneProcessor extends AbstractTemplateProcessor<String> {

    @Override
    protected boolean accept(String declPort, Set<CertType> types) {
        Set<CertType> selfTypes = getTypes();
        //如果传递过来的types 中 包含这个 processor 的 type，则返回true 去执行doProcess 方法
        if(CollectionUtils.containsAny(types,selfTypes)){
            return true;
        }
        return false;
    }

    @Override
    protected Response<Void> doProcess(String useId) {
        log.info("执行 OneProcessor 的逻辑"+useId);
        //将返回值放入到 DataHolder中
        DataHolder.get().getResultMaps().put("One","OneProcessor");
        return Response.success();
    }

    @Override
    public Set<CertType> getTypes() {
        Set<CertType> set = new HashSet<>();
        set.add(CertType.TYPE_1);
        return set;
    }

    @Override
    public String supply(ParamList paramList) {
        return paramList.getUseId();
    }
}
