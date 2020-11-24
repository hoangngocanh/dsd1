package com.skyrone.drone.demo.dto;

public class ServerResponseDto{

    private ResponseStatus status;
    private Object data;

    public ServerResponseDto(ResponseStatus responseStatus) {
        this.status = responseStatus;
    }

    public ServerResponseDto(ResponseStatus responseStatus, Object data) {
        this.status = responseStatus;
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}