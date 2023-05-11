package com.dream.demo.utils;


import com.alibaba.fastjson.JSONObject;

/**
 * 数据返回类
 */
public class RespResult {

    private static final String SUCCESS = "1";   //成功
    private static final String FAIL = "0";// 失败

    private String code;
    private String msg;
    private String createTime;
    private Object data;


    public RespResult() {
    }

    public RespResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RespResult success(Object data, String msg, String createTime) {
        RespResult Result = new RespResult();
        Result.setCode(SUCCESS);
        Result.setCreateTime(createTime);
        Result.setData(data);
        Result.setMsg(msg);
        return Result;
    }

    public static RespResult success(Object data) {
        RespResult Result = new RespResult();
        Result.setCode(SUCCESS);
        Result.setData(data);
        Result.setMsg("请求成功");
        return Result;
    }

    /**
     * 通用请求成功方法
     *
     * @return
     */
    public static RespResult success() {
        RespResult Result = new RespResult();
        Result.setCode(SUCCESS);
        Result.setMsg("成功");
        return Result;
    }

    public static RespResult failure(String msg) {
        RespResult Result = new RespResult();
        Result.setCode(FAIL);
        Result.setMsg(msg);
        return Result;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("createTime", createTime);
        jsonObject.put("data", data);
        return jsonObject.toString();
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
