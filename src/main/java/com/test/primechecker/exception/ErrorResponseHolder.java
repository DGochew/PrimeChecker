package com.test.primechecker.exception;

public class ErrorResponseHolder {

    private String message;
    private int statusCode;
    private String cause;

    public ErrorResponseHolder(String message, int statusCode, String cause) {
        super();
        this.message = message;
        this.statusCode = statusCode;
        this.cause = cause;
    }

    public String getMessage() {

        return message;
    }

    public int getStatusCode() {

        return statusCode;
    }

    public String getCause() {

        return cause;
    }

}
