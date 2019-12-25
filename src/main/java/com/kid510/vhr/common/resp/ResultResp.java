package com.kid510.vhr.common.resp;

import java.io.Serializable;

/**
 * @Classname ResultResp
 * @Description 通用的响应
 * @Date 2019/12/25 17:57
 * @Author kid
 */
public class ResultResp implements Serializable {

    private static final long serialVersionUID = 862105743631984065L;
    private Integer status;
    private String message;
    private Object data;

    public static ResultResp ok(String message) {
        return new ResultResp(200, message, null);
    }

    public static ResultResp ok(String message, Object data) {
        return new ResultResp(200, message, data);
    }


    public static ResultResp error(String message) {
        return new ResultResp(500, message, null);
    }

    public static ResultResp error(String message, Object data) {
        return new ResultResp(500, message, data);
    }


    private ResultResp(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private ResultResp() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
