package com.aadhar.vault.payload;

import java.util.List;

public class ResponseMessage<T> {
    private Integer status;
    private String message;
    private T data;
    private List<?> dataList;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public ResponseMessage(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
    }


    public ResponseMessage(Integer status, String message, T data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage(Integer status, String message, List<?> dataList) {
        super();
        this.status = status;
        this.message = message;
        this.dataList = dataList;
    }
}
