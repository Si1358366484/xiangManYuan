package com.xmy.utils;

import com.github.pagehelper.PageHelper;
import com.xmy.domain.page.PageDomain;

/**
 * 分页工具类
 */
public class PageUtils {
    /**
     * 开启分页功能
     */
    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        startPage(pageDomain);
    }

    /**
     * 开启分页功能
     *
     * @param pageDomain 分页参数
     */
    public static void startPage(PageDomain pageDomain) {
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = pageDomain.getOrderBy();
        Boolean reasonable = pageDomain.getReasonable();

        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageHelper.getLocalPage().setReasonable(reasonable);
    }

    /**
     * 清除分页参数
     */
    public static void clearPage() {
        PageHelper.clearPage();
    }
}