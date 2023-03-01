package com.dne.core.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelListener.class);


    private List<T> datas = new ArrayList<>();

    private Integer lastRowNum = 0;

    @Override
    public void invoke(T object, AnalysisContext context) {
        lastRowNum = context.getCurrentRowNum();
        datas.add(object);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(object);//根据自己业务做处理
    }

    private void doSomething(Object object) {
        //1、入库调用接口
        log.debug("read Object: {}",object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public Integer getLastRowNum() {
        return lastRowNum;
    }

    public void setLastRowNum(Integer lastRowNum) {
        this.lastRowNum = lastRowNum;
    }
}
