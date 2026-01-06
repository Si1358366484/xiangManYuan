package com.xmy.controller;

import com.xmy.domain.common.AjaxResult;
import com.xmy.domain.entity.DishCategory;
import com.xmy.domain.page.TableDataInfo;
import com.xmy.service.DishCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("xmy/dishCategory")
public class DishCategoryController extends BaseController {
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
        // 设置创建人和更新人为admin，时间为当前时间
        dishCategory.setDbstatus(1);
        dishCategory.setCreateBy("admin");
        dishCategory.setUpdateBy("admin");
        dishCategory.setCreateTime(new Date());
        dishCategory.setUpdateTime(new Date());
        return AjaxResult.success(dishCategoryService.save(dishCategory));
    }
    @PutMapping
    public AjaxResult updateDishCategory(@RequestBody DishCategory dishCategory) {
        // 设置更新人为admin，时间为当前时间
        dishCategory.setDbstatus(1);
        dishCategory.setUpdateBy("admin");
        dishCategory.setUpdateTime(new Date());
        return AjaxResult.success(dishCategoryService.updateById(dishCategory));
    }
    /**
     * 批量逻辑删除dishCategory
     */
    @DeleteMapping("/{ids}")
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

    // ==================== 特殊处理逻辑 ====================
    /**
     * 分页获取菜品分类列表
     */
    @GetMapping("/pageList")
    public TableDataInfo getDishCategoryPageList(DishCategory dishCategory) {
        // 开启分页
        startPage();
        // 调用service层方法获取数据
        List<DishCategory> list = dishCategoryService.getDishCategoryList(dishCategory);
        // 返回分页结果
        return getDataTable(list);
    }
}