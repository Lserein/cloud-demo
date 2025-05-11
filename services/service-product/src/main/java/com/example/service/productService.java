package com.example.service;

import com.example.bean.product;
import org.springframework.stereotype.Service;

@Service
public interface productService {

    public product getProductById(Long productId);

}
