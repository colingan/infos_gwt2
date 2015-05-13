package com.github.colingan.client.common.rpc.exception;

import com.google.gwt.user.client.rpc.RpcTokenException;

/**
 * 继承RpcTokenException是为了利用TokenProtect功能，在前端判断，如果登陆失效，则rpc请求将自动刷新页面，重新通过bind登陆
 * @title NotLoginException
 * @description TODO 
 * @author akeungsu
 * @date 2014-10-31
 * @version 1.0
 */
public class NotLoginException extends RpcTokenException {

    private static final long serialVersionUID = 6487804886897753987L;
    private int code;

    public NotLoginException() {
        super();
    }

    public NotLoginException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        String msg = getMessage();
        String info = (msg != null && msg.length() > 0) ? ",message=" + msg : "";
        return "NotLoginException[errorCode=" + code + info + "]";
    }

}
