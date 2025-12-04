package com.xmy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.domain.Booth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoothMapper extends BaseMapper<Booth> {
    List<Booth> selectBoothList(Booth booth);
}
