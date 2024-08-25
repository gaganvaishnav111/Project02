package org.revature.RevTaskManagement.models;

public class BaseResponse<T> {
    private T data;
    private String properties;

    public BaseResponse() {
    }

    public BaseResponse(T data, String properties) {
        this.data = data;
        this.properties = properties;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
