package com.xmy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.domain.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish>{
    List<Dish> getDishList(Dish dish);
    
    /**
     * 批量逻辑删除dish
     * @param ids dish id数组
     * @return 影响行数
     */
    int batchDeleteDish(Long[] ids);
}
