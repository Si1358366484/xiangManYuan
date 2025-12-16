package com.xmy.domain.entity;

import lombok.Data;

@Data
public class BaseEntity {
    // 通用字段
    private Integer id;
    // 审计字段
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private Integer dbstatus;
}