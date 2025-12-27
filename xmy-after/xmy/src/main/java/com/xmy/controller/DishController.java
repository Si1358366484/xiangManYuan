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
    @GetMapping("/list")
    public AjaxResult getDishList(Dish dish) {
        return AjaxResult.success(dishService.list());
    }
    @GetMapping("/{id}")
    public AjaxResult getDishById(@PathVariable Long id) {
        return AjaxResult.success(dishService.getById(id));
    }
    @PostMapping
    public AjaxResult addDish(@RequestBody Dish dish) {
        return AjaxResult.success(dishService.save(dish));
    }
    @PutMapping
    public AjaxResult updateDish(@RequestBody Dish dish) {
        return AjaxResult.success(dishService.updateById(dish));
    }
    @DeleteMapping("/{id}")
    public AjaxResult deleteDish(@PathVariable Long id) {
        return AjaxResult.success(dishService.removeById(id));
    }
}
