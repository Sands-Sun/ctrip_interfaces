package com.dne.core.common;

import com.dne.core.util.DateUtils;

import java.util.Map;

public  abstract class BaseAbsRiverBiz {


    protected String getBatchNo() {
        return DateUtils.getCustomDate(0).replace("-", "") + (int)((Math.random() * 9 + 1) * 100000);
    }

    /**
     * 处理日志
     * @param dataMap
     */
    public abstract String processLog(Map<String, Object> dataMap);

    /**
     * 处理数据
     */
    public abstract void processData(Map<String, Object> dataMap);
}
