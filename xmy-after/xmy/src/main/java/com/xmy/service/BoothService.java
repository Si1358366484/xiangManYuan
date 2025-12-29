package com.xmy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.domain.entity.Booth;
import com.xmy.domain.dto.BoothStatusCount;

import java.util.List;

public interface BoothService extends IService<Booth> {
    List<Booth> getBoothList(Booth booth);

    int updateBooth(Booth booth);

    BoothStatusCount getBoothStatusCount();
    
    /**
     * 批量逻辑删除booth
     * @param ids booth id数组
     * @return 影响行数
     */
    int batchDeleteBooth(Long[] ids);
}
