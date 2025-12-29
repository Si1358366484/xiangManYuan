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
        return AjaxResult.success(dishService.getDishList(dish));
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
    /**
     * 批量逻辑删除dish
     */
    @DeleteMapping("/batchDelete/{ids}")
    public AjaxResult batchDeleteDish(@PathVariable("ids") Long[] ids) {
        // 参数验证
        if (ids == null || ids.length == 0) {
            return AjaxResult.error("请选择要删除的菜品");
        }
        // 执行批量删除
        int deletedCount = dishService.batchDeleteDish(ids);
        // 检查删除结果
        if (deletedCount <= 0) {
            return AjaxResult.error("删除失败，未找到匹配的菜品记录");
        }
        // 返回成功结果
        return AjaxResult.success("删除成功，共删除 " + deletedCount + " 条记录");
    }
}
