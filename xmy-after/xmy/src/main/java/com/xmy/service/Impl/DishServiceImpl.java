package com.xmy.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.domain.entity.Dish;
import com.xmy.mapper.DishMapper;
import com.xmy.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<Dish> getDishList(Dish dish) {
        return dishMapper.getDishList(dish);
    }
}
