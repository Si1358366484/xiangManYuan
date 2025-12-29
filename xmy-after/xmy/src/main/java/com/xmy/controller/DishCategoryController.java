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
        return AjaxResult.success(dishCategoryService.getDishCategoryList(dishCategory));
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
    /**
     * 批量逻辑删除dishCategory
     */
    @DeleteMapping("/batchDelete/{ids}")
    public AjaxResult batchDeleteDishCategory(@PathVariable("ids") Long[] ids) {
        // 参数验证
        if (ids == null || ids.length == 0) {
            return AjaxResult.error("请选择要删除的菜品分类");
        }
        // 执行批量删除
        int deletedCount = dishCategoryService.batchDeleteDishCategory(ids);
        // 检查删除结果
        if (deletedCount <= 0) {
            return AjaxResult.error("删除失败，未找到匹配的菜品分类记录");
        }
        // 返回成功结果
        return AjaxResult.success("删除成功，共删除 " + deletedCount + " 条记录");
    }
}