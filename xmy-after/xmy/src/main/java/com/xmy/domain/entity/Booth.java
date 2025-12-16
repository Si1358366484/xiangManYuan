package com.xmy.domain.entity;

import lombok.Data;

@Data
public class Booth extends BaseEntity {
    private String boothName;
    private String boothCapacity;
    private String boothStatus;
    private Double boothPrice;
    private String boothTime;
    private String boothType;
    // ==================== 特殊处理逻辑 ====================

}

