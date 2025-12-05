package com.xmy.domain.dto;

import lombok.Data;

/**
 * 获取卡座状态统计类
 */
@Data
public class BoothStatusCount {
    //总数
    private Integer total;
    //空闲
    private Integer freeCount;
    //占用数
    private Integer occupyCount;
    //已预约数
    private Integer reserveCount;
}

