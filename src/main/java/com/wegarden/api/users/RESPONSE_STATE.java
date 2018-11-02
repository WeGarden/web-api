package com.wegarden.api.users;

public enum RESPONSE_STATE {
    SUCCEED(0),
    USERNAME_ALREADY_USED(1),
    EMAIL_ALREADY_USED(2)
    ;

    private final int code;

    RESPONSE_STATE(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
