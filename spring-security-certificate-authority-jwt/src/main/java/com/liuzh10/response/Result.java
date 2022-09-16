package com.liuzh10.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    /**
     * response is success or fail
     */
    private Boolean success;

    /**
     * response code
     */
    private Integer code;

    /**
     * response message,success message or fail message
     */
    private String message;

    /**
     * response data
     */
    private Map<String, Object> data = new HashMap<>();


    private Result() {

    }


    public static Result ok() {
        Result result = new Result();
        result.setSuccess(Boolean.TRUE);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setSuccess(Boolean.FALSE);
        result.setCode(ResultCode.COMMON_FAIL.getCode());
        result.setMessage(ResultCode.COMMON_FAIL.getMessage());
        return result;
    }


    public static Result fail(ResultCode resultCode) {
        Result result = new Result();
        result.setSuccess(Boolean.FALSE);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }


    public Result ok(Boolean success) {
        this.setSuccess(success);
        return this;
    }


    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


}
