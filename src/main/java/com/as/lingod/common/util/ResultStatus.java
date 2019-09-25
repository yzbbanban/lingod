package com.as.lingod.common.util;

/**
 * Created by brander on 2019/1/12
 */
public enum ResultStatus {
    OK(200, "SUCCESS"),
    NO_AUTH(401, "NO_AUTH"),
    ERROR(500, "ERROR");
    private Integer code;

    private String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
