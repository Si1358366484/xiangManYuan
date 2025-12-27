package com.xmy.controller;

import com.xmy.domain.common.AjaxResult;
import com.xmy.domain.entity.Booth;
import com.xmy.domain.dto.BoothStatusCount;
import com.xmy.domain.vo.BoothVo;
import com.xmy.service.BoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("xmy/booth")
public class BoothController {
    @Autowired
    private BoothService boothService;
    // ==================== 常规CRUD ====================
    //获取卡座列表
    @GetMapping("/list")
    public AjaxResult getBoothList(Booth booth) {
        BoothVo boothVo = new BoothVo();
        List<Booth> boothList = boothService.getBoothList(booth);
        BoothStatusCount boothStatusCount = boothService.getBoothStatusCount();
        boothVo.setBoothList(boothList);
        boothVo.setBoothStatusCount(boothStatusCount);
        return AjaxResult.success(boothVo);
    }
    //修改卡座
    @PutMapping
    public AjaxResult updateBooth(Booth booth) {
        if (booth.getId() == null){
            return AjaxResult.error("卡座ID不能为空");
        }
        try {
            booth.setDbstatus(1);
            boothService.updateBooth(booth);
            return AjaxResult.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("修改失败：" + e.getMessage());
        }
    }

    //新增卡座
    @PostMapping
    public AjaxResult addBooth(@RequestBody Booth booth) {
        return AjaxResult.success(boothService.save(booth));
    }
    
    //根据ID获取卡座
    @GetMapping("/{id}")
    public AjaxResult getBoothById(@PathVariable Long id) {
        return AjaxResult.success(boothService.getById(id));
    }
    
    //删除卡座
    @DeleteMapping("/{id}")
    public AjaxResult deleteBooth(@PathVariable Long id) {
        return AjaxResult.success(boothService.removeById(id));
    }
    
    // ==================== 特殊处理逻辑 ====================

}
