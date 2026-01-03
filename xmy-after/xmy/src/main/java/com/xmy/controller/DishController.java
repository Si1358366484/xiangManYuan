package com.xmy.controller;

import com.xmy.domain.common.AjaxResult;
import com.xmy.domain.entity.Dish;
import com.xmy.domain.page.TableDataInfo;
import com.xmy.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("xmy/dish")
public class DishController extends BaseController {
    @Autowired
    private DishService dishService;
    // ==================== 常规CRUD ====================
    /**
     * 分页获取菜品列表
     */
    @GetMapping("/list")
    public TableDataInfo getDishList(Dish dish) {
        // 开启分页
        startPage();
        // 调用service层方法获取数据
        List<Dish> list = dishService.getDishList(dish);
        // 返回分页结果
        return getDataTable(list);
    }

    /**
     * 获取所有菜品列表
     */
    @GetMapping("/list/all")
    public AjaxResult getAllDishList(Dish dish) {
        return AjaxResult.success(dishService.getDishList(dish));
    }
    @GetMapping("/{id}")
    public AjaxResult getDishById(@PathVariable Long id) {
        return AjaxResult.success(dishService.getById(id));
    }
    @PostMapping
    public AjaxResult addDish(@RequestBody Dish dish) {
        // 设置创建人和更新人为admin，时间为当前时间
        dish.setCreateBy("admin");
        dish.setUpdateBy("admin");
        dish.setCreateTime(new Date());
        dish.setUpdateTime(new Date());
        return AjaxResult.success(dishService.save(dish));
    }
    @PutMapping
    public AjaxResult updateDish(@RequestBody Dish dish) {
        // 设置更新人为admin，时间为当前时间
        dish.setUpdateBy("admin");
        dish.setUpdateTime(new Date());
        return AjaxResult.success(dishService.updateById(dish));
    }
    /**
     * 批量逻辑删除dish
     */
    @DeleteMapping("/{ids}")
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
