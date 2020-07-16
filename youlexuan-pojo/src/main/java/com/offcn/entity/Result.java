package com.offcn.entity;

import java.io.Serializable;

public class Result implements Serializable {
    /**
     * 返回结果封装
     * @author Administrator
     *
     */
    private boolean success;
    private String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
