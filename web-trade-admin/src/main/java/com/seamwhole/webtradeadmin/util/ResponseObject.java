package com.seamwhole.webtradeadmin.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class ResponseObject extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public ResponseObject() {
        put("code", 0);
    }

    public static ResponseObject error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static ResponseObject error(String msg) {
        return error(500, msg);
    }

    public static ResponseObject error(int code, String msg) {
        ResponseObject r = new ResponseObject();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResponseObject ok(String msg) {
        ResponseObject r = new ResponseObject();
        r.put("msg", msg);
        return r;
    }

    public static ResponseObject ok(Map<String, Object> map) {
        ResponseObject r = new ResponseObject();
        r.putAll(map);
        return r;
    }

    public static ResponseObject ok() {
        return new ResponseObject();
    }

    public ResponseObject put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
