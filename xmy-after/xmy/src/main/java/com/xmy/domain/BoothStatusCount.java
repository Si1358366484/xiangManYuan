package com.xmy.domain;
//获取卡座状态统计类

import lombok.Data;

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
