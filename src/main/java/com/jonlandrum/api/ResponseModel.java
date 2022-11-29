package com.jonlandrum.api;

public class ResponseModel<T> {
    private Boolean status;
    private String message;
    private T data;


    public Boolean getStatus() {
        return status;
    }
    public void setStatus(final Boolean status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(final String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(final T data) {
        this.data = data;
    }
}
