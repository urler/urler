package com.urler.value_objects;

import com.urler.enums.ResponseStatus;

public class Response {

    private ResponseStatus status;
    private String message;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Response createErrorResponse() {
        Response result = new Response();
        result.setMessage("the URL you provided is not valid");
        result.setStatus(ResponseStatus.BAD);
        return result;
    }
}
