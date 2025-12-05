package com.xmy.service.Impl;

import com.xmy.domain.Booth;
import com.xmy.mapper.BoothMapper;
import com.xmy.service.BoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoothServiceImpl implements BoothService {
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
}
