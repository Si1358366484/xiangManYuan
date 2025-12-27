package com.xmy.controller;

import com.xmy.domain.common.AjaxResult;
import com.xmy.domain.entity.Dish;
import com.xmy.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("xmy/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    // ==================== 常规CRUD ====================
    //获取卡座列表
    @GetMapping("/list")
    public AjaxResult getDishList(Dish dish) {
        return AjaxResult.success(dishService.list());
    }
}
