package com.xmy.service;

import com.xmy.domain.Booth;
import com.xmy.domain.BoothStatusCount;

import java.util.List;

public interface BoothService {
    List<Booth> getBoothList(Booth booth);

    int updateBooth(Booth booth);

    BoothStatusCount getBoothStatusCount();
}
