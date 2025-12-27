package com.xmy.domain.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class BaseEntity {
    // 通用字段
    private Long id;
    // 审计字段
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private Integer dbstatus;
}