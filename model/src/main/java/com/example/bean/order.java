package com.example.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class order {

    private Long id;                       //订单id
    private BigDecimal totalAmount;        //订单总金额
    private Long userId;                   //用户id
    private String nikeName;               //用户昵称
    private String address;                //用户地址
    private List<product> productList;      //商品列表

}
