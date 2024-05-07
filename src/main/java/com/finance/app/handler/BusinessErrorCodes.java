package com.finance.app.handler;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;


public enum BusinessErrorCodes {

    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, HttpStatus.BAD_REQUEST, "Current Password Is Incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, HttpStatus.BAD_REQUEST, "The new password does not match"),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN, "User account is not locked"),
    ACCOUNT_DISABLED(303, HttpStatus.FORBIDDEN, "User account is disabled"),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN, "Login and / or password is incorrect"),

    ;

    @Getter
    private final int code;

    @Getter
    private final String description;

    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus status, String description){
        this.code = code;
        this.description = description;
        this.httpStatus = status;

    }


}
