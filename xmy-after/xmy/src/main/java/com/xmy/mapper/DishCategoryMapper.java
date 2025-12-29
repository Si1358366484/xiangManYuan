package com.xmy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.domain.entity.DishCategory;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DishCategoryMapper extends BaseMapper<DishCategory>{
    List<DishCategory> getDishCategoryList(DishCategory dishCategory);
}