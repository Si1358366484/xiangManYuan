package com.xmy.domain;

import lombok.Data;

@Data
public class Booth {
    private Integer id;
    private String boothName;
    private String boothCapacity;
    private String boothStatus;
    private Double boothPrice;
    private String boothTime;
    private String boothType;
    //通用字段
    private Integer dbstatus;
    // ==================== 特殊处理逻辑 ====================

}
