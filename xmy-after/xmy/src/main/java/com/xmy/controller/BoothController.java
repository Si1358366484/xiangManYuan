package com.xmy.controller;

import com.xmy.domain.AjaxResult;
import com.xmy.domain.Booth;
import com.xmy.service.BoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("xmy/booth")
public class BoothController {
    @Autowired
    private BoothService boothService;
    @GetMapping("/list")
    public AjaxResult getBoothList() {
        List<Booth> boothList = boothService.getBoothList();
        return AjaxResult.success(boothList);
    }
}
