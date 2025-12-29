package com.xmy.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.domain.entity.Booth;
import com.xmy.domain.dto.BoothStatusCount;
import com.xmy.mapper.BoothMapper;
import com.xmy.service.BoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoothServiceImpl extends ServiceImpl<BoothMapper, Booth> implements BoothService {
    @Autowired
    private BoothMapper boothMapper;
    @Override
    public List<Booth> getBoothList(Booth booth) {
        return boothMapper.selectBoothList(booth);
    }

    @Override
    public int updateBooth(Booth booth) {
        return boothMapper.updateById(booth);
    }
    //获取卡座状态统计
    @Override
    public BoothStatusCount getBoothStatusCount() {
        return boothMapper.selectBoothStatusCount();
    }

    @Override
    public int batchDeleteBooth(Long[] ids) {
        return boothMapper.batchDeleteBooth(ids);
    }
}
