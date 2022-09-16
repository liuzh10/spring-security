package com.liuzh10.utli;

public enum ResultCode implements CustomizeResultCode {

    SUCCESS(200, "操作成功"),
    COMMON_FAIL(999, "操作异常");

    private Integer code;

    private String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
