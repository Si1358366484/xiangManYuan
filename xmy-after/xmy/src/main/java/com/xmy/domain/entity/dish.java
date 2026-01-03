package com.xmy.domain.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Dish extends BaseEntity {
    private Long categoryId;
    private String dishName;
    private BigDecimal dishPrice;
    private String dishImage;
    private Integer salesStatus;
}
