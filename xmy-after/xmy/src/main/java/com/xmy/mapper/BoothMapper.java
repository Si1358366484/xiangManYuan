package com.xmy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.domain.entity.Booth;
import com.xmy.domain.dto.BoothStatusCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoothMapper extends BaseMapper<Booth> {
    List<Booth> selectBoothList(Booth booth);

    //获取卡座状态统计
    BoothStatusCount selectBoothStatusCount();
    
    /**
     * 批量逻辑删除booth
     * @param ids booth id数组
     * @return 影响行数
     */
    int batchDeleteBooth(Long[] ids);

}
