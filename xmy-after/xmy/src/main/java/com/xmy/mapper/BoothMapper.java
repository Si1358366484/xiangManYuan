package com.xmy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.domain.Booth;
import com.xmy.domain.BoothStatusCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoothMapper extends BaseMapper<Booth> {
    List<Booth> selectBoothList(Booth booth);

    //获取卡座状态统计
    BoothStatusCount selectBoothStatusCount();

}
