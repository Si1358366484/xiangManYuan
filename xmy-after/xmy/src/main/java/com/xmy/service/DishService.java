package com.xmy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.domain.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    List<Dish> getDishList(Dish dish);
    
    /**
     * 批量逻辑删除dish
     * @param ids dish id数组
     * @return 影响行数
     */
    int batchDeleteDish(Long[] ids);
}
