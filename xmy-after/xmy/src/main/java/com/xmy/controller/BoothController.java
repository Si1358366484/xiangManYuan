package com.xmy.controller;

import com.xmy.domain.AjaxResult;
import com.xmy.domain.Booth;
import com.xmy.service.BoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("xmy/booth")
public class BoothController {
    @Autowired
    private BoothService boothService;
    // ==================== 特殊处理逻辑 ====================
    //@GetMapping("/getTotal")

    // ==================== 常规CRUD ====================
    //获取卡座列表
    @GetMapping("/list")
    public AjaxResult getBoothList(Booth booth) {
        List<Booth> boothList = boothService.getBoothList(booth);
        System.out.println(boothList);
        return AjaxResult.success(boothList);
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
}
