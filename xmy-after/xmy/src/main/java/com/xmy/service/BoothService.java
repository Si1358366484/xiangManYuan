package com.xmy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.domain.entity.Booth;
import com.xmy.domain.dto.BoothStatusCount;

import java.util.List;

public interface BoothService extends IService<Booth> {
    List<Booth> getBoothList(Booth booth);

    int updateBooth(Booth booth);

    BoothStatusCount getBoothStatusCount();
}
