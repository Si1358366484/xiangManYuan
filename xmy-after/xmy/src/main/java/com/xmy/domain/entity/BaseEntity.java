package com.xmy.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;

@Data
public class BaseEntity {
    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;
    // 审计字段
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private Integer dbstatus;
}