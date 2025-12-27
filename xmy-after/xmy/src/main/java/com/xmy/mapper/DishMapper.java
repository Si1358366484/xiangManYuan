package com.xmy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.domain.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish>{
}
