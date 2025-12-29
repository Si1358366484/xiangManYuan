package com.xmy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.domain.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    List<Dish> getDishList(Dish dish);
}
