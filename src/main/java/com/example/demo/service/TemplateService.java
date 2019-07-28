package com.example.demo.service;

import com.example.demo.entity.CertType;
import com.example.demo.entity.ParamList;
import com.example.demo.intercept.DataHolder;
import com.example.demo.processor.TemplateProcessorMananger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class TemplateService {

    /**
     * 业务方法
     */
    public void doTemplate(){

        // 准备参数
        ParamList paramList = new ParamList();
        paramList.setUseId("6666");

        Set<CertType> sets = new HashSet<>();
        sets.add(CertType.TYPE_3);
        sets.add(CertType.TYPE_1);
        TemplateProcessorMananger.getInstance().process(paramList,sets);

        //预期 只会执行 1 ，3的processor
        Map<String, Object> resultMaps = DataHolder.get().getResultMaps();
        for (String s : resultMaps.keySet()) {
            log.info(s+"------>"+resultMaps.get(s));
        }

    }
}
