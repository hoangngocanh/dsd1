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
}
