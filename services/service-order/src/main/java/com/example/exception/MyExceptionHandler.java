package com.example.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.bean.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class MyExceptionHandler implements BlockExceptionHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter writer = httpServletResponse.getWriter();
        R error = R.error(500, "系统繁忙，请稍后再试");
        String json = objectMapper.writeValueAsString(error);
        writer.write(json);
    }
}
