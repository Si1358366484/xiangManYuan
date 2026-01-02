package com.xmy.controller;

import com.github.pagehelper.PageInfo;
import com.xmy.domain.page.TableDataInfo;
import com.xmy.utils.PageUtils;

import java.util.List;

/**
 * 基础控制器，提供分页相关的通用方法
 */
public class BaseController {
    /**
     * 开启分页功能
     */
    protected void startPage() {
        PageUtils.startPage();
    }

    /**
     * 响应返回结果
     *
     * @param list 数据列表
     * @return 分页响应对象
     */
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
}