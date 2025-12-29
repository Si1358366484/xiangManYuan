package com.xmy.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.domain.entity.DishCategory;
import com.xmy.mapper.DishCategoryMapper;
import com.xmy.service.DishCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishCategoryServiceImpl extends ServiceImpl<DishCategoryMapper, DishCategory> implements DishCategoryService {
    @Autowired
    private DishCategoryMapper dishCategoryMapper;

    @Override
    public List<DishCategory> getDishCategoryList(DishCategory dishCategory) {
        return dishCategoryMapper.getDishCategoryList(dishCategory);
    }

    @Override
    public int batchDeleteDishCategory(Long[] ids) {
        return dishCategoryMapper.batchDeleteDishCategory(ids);
    }
}