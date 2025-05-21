package com.example.bean.common;

import lombok.Data;

@Data
public class R {
    private int code;
    private String message;
    private Object data;

    public static R ok() {
        R r = new R();
        r.setCode(200);
        return r;
    }

    public static R ok(String message,Object data) {
        R r = new R();
        r.setCode(200);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(500);
        return r;
    }

    public static R ok(int code, String message)  {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static R error(int code, String message)  {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }
}
