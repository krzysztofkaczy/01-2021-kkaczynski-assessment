package com.kkaczynski.assessment.rest.v1.model;

public class ApiResponse {
    public enum Status {
        OK(0), ERROR(1), UNAUTHORIZED(2), INVALID(3), UNSUPPORTED_OPERATION(4);

        private int code;

        private Status(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    private int statusCode;
    private String message;
    private Object value;

    public ApiResponse(int statusCode, String message, Object value) {
        this.statusCode = statusCode;
        this.message = message;
        this.value = value;
    }

    public ApiResponse(Status statusCode, String message, Object value) {
        this.statusCode = statusCode.getCode();
        this.message = message;
        this.value = value;
    }

    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ApiResponse(Status status, String message) {
        this.statusCode = status.getCode();
        this.message = message;
    }

    public ApiResponse(Object value) {
        this.statusCode = Status.OK.getCode();
        this.value = value;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getValue() {
        return this.value;
    }

}