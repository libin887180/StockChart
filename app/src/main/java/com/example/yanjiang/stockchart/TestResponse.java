package com.example.yanjiang.stockchart;

import java.util.List;

/**
 * Created by libin on 2017/10/20.
 */

public class TestResponse {
    private  String  code;
    private  String  msg;
    private List<TestData> body;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TestData> getBody() {
        return body;
    }

    public void setBody(List<TestData> body) {
        this.body = body;
    }
}
