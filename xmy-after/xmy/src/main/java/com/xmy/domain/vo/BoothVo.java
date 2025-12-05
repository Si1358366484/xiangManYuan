package com.xmy.domain.vo;

import com.xmy.domain.entity.Booth;
import com.xmy.domain.dto.BoothStatusCount;
import lombok.Data;

import java.util.List;

@Data
public class BoothVo {
    private List<Booth> boothList;
    private BoothStatusCount boothStatusCount;
}
