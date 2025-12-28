package com.xmy.controller;

import com.xmy.domain.common.AjaxResult;
import com.xmy.domain.entity.DishCategory;
import com.xmy.service.DishCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("xmy/dishCategory")
public class DishCategoryController {
    @Autowired
    private DishCategoryService dishCategoryService;
    // ==================== 常规CRUD ====================
    @GetMapping("/list")
    public AjaxResult getDishCategoryList(DishCategory dishCategory) {
        return AjaxResult.success(dishCategoryService.list());
    }
    @GetMapping("/{id}")
    public AjaxResult getDishCategoryById(@PathVariable Long id) {
        return AjaxResult.success(dishCategoryService.getById(id));
    }
    @PostMapping
    public AjaxResult addDishCategory(@RequestBody DishCategory dishCategory) {
        return AjaxResult.success(dishCategoryService.save(dishCategory));
    }
    @PutMapping
    public AjaxResult updateDishCategory(@RequestBody DishCategory dishCategory) {
        return AjaxResult.success(dishCategoryService.updateById(dishCategory));
    }
    @DeleteMapping("/{id}")
    public AjaxResult deleteDishCategory(@PathVariable Long id) {
        return AjaxResult.success(dishCategoryService.removeById(id));
    }
}