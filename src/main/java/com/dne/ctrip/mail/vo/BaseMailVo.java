package com.dne.ctrip.mail.vo;

import com.dne.core.basic.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseMailVo extends BaseEntity {

    private String attachment;
    private String mailType;

    private String mailTo;

    private String errorLog;

    public BaseMailVo(String mailType) {
        this.mailType = mailType;
    }
}
