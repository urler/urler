package com.urler.value_objects;

import com.urler.enums.ResponseStatus;

public class ApiResponse {

    private ResponseStatus status;
    private String id;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(String id) {
        this.id = id;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ApiResponse createErrorResponse(String error, String id) {
        ApiResponse result = new ApiResponse();
        result.setMessage(error);
        result.setStatus(ResponseStatus.BAD);
        result.setId(id);
        return result;
    }

}
