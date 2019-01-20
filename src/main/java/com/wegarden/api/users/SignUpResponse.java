package com.wegarden.api.users;

import io.swagger.annotations.ApiModelProperty;

public class SignUpResponse {

    @ApiModelProperty(notes = "The status of the signup request which can be : \n" +
            "- SUCCEED : 0\n " +
            "- USERNAME_ALREADY_USED : 1\n " +
            "- EMAIL_ALREADY_USED : 2")
    private int status;
    @ApiModelProperty("A comprehensive message of the request status")
    private String message;

    public SignUpResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
