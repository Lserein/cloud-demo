package com.example.bean;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class product {
    private Long id;
    private String productName;
    private BigDecimal price;
    private int num;

}
