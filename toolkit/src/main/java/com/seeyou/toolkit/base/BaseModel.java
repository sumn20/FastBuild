package com.seeyou.toolkit.base;


import java.io.Serializable;

public class BaseModel<T> implements Serializable {

    /**
     * code 1表示成功获取
     * code 0表示参数异常
     * code -1表示获取失败
     *
     * code 其他 详见服务器
     *
     */

    /**
     * code : 0
     * message : 获取成功
     * timestamp : 2017-12-06 16:42:34
     */

    private int code;
    private int dataCode;

    private String message;

    private String timestamp;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDataCode() {
        return dataCode;
    }

    public void setDataCode(int dataCode) {
        this.dataCode = dataCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
