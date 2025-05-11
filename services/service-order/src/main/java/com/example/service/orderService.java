package com.example.service;

import com.example.bean.order;
import org.springframework.stereotype.Service;

@Service
public interface orderService {

    order CreateOrder(Long userId, Long productId);

}
