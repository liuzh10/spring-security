package com.liuzh10.exception;

public class MyException extends RuntimeException {

    /**
     * HttpStatus code
     */
    private Integer code;


    public MyException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
