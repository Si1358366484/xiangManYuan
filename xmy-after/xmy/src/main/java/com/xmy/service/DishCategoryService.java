package com.xmy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.domain.entity.DishCategory;

import java.util.List;

public interface DishCategoryService extends IService<DishCategory> {
    List<DishCategory> getDishCategoryList(DishCategory dishCategory);
}