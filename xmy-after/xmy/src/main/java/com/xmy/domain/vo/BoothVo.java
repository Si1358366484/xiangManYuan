package com.xmy.domain.vo;

import com.xmy.domain.Booth;
import com.xmy.domain.BoothStatusCount;
import lombok.Data;

import java.util.List;

@Data
public class BoothVo {
    private List<Booth> boothList;
    private BoothStatusCount boothStatusCount;
}
