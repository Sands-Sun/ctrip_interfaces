package com.dne.core.common;

public enum ErrorEnum {
    EMP_SYNC_API_ERROR("同步员工信息到携程API错误", 1000),

    EMP_READ_FILE_ERROR("读取员工信息文件出错", 1100),
    ORDER_STATEMENT_READ_FILE_ERROR ("对账单读取文件出错", 3000),
    ORDER_STATEMENT_SEND_ISSUE_ORDER_EMAIL("发送携程对账有问题订单明细出错", 3200);

    private ErrorEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

    private String message;
    private int code;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
