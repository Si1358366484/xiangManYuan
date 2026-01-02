package com.xmy.domain.page;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;

/**
 * 分页数据
 *
 * @author ruoyi
 */
@Data
public class PageDomain
{
    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String orderByColumn;

    /** 排序的方向desc或者asc */
    private String isAsc = "asc";

    /** 分页参数合理化 */
    private Boolean reasonable = true;

    /**
     * 生成排序语句
     */
    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StringUtils.camelToUnderline(orderByColumn) + " " + isAsc;
    }

    /**
     * 设置排序方向，兼容前端排序类型
     */
    public void setIsAsc(String isAsc)
    {
        if (StringUtils.isNotEmpty(isAsc))
        {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc))
            {
                isAsc = "asc";
            }
            else if ("descending".equals(isAsc))
            {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }

    /**
     * 获取分页参数合理化设置，默认true
     */
    public Boolean getReasonable()
    {
        return reasonable == null ? Boolean.TRUE : reasonable;
    }
}
