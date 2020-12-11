package com.skyrone.drone.demo.dto;

public interface ResponseCase {
    ResponseStatus SUCCESS = new ResponseStatus(1000, "SUCCESS");
    ResponseStatus ADD_SUCCESS = new ResponseStatus(2000, "add success");
    ResponseStatus UPDATE_SUCCESS = new ResponseStatus(2001, "update  success");
    ResponseStatus DELETE_SUCCESS = new ResponseStatus(2002, "delete  success");
    ResponseStatus DELETE_FAILED = new ResponseStatus(2005, "ERROR");
    ResponseStatus OBJECT_NULL = new ResponseStatus(2003, "ERROR");
    ResponseStatus PASSWORD_NOT_RIGHT = new ResponseStatus(1007, "Pass word is in correct");
    ResponseStatus EMAIL_IS_USED = new ResponseStatus(1, "Email is used");
    ResponseStatus ERROR = new ResponseStatus(4, "Error");
    ResponseStatus NOT_FOUND_DRONE = new ResponseStatus(4000, "not found drone");
    ResponseStatus DRONE_MAINTENANCE = new ResponseStatus(4001, "drone maintenance");
    ResponseStatus DRONE_NOT_FLIGHT = new ResponseStatus(4002, "drone not flight");
    ResponseStatus DRONE_BUSY = new ResponseStatus(4003, "drone is busy");

    ResponseStatus NOT_FOUND_FLIGHT = new ResponseStatus(4004, "not found flight path please check id flight path");
    ResponseStatus DRONE_AVAILABLE = new ResponseStatus(4005, "drone rảnh");
    ResponseStatus NOT_DATA = new ResponseStatus(4006, "không tìm thấy");
    ResponseStatus DRONE_CHARGING = new ResponseStatus(4007, "drone charging");
}
