package io.github.ooknight.universe.core.client.web;

import java.io.Serializable;

public class ResponseWrap implements Serializable {

    private final int code;
    private final String message;
    private final Object data;

    private ResponseWrap(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseWrap success() {
        return new ResponseWrap(200, "", null);
    }

    public static ResponseWrap success(Object data) {
        return new ResponseWrap(200, "", data);
    }

    public static ResponseWrap error(int code, String message) {
        return new ResponseWrap(code, message, null);
    }

    public static ResponseWrap error(int code, String message, Object data) {
        return new ResponseWrap(code, message, data);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}
