package com.cruiseline.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> fieldErrors;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(Instant timestamp, int status, String error, String message, String path, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public int getStatus() {
        return this.status;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPath() {
        return this.path;
    }

    public Map<String, String> getFieldErrors() {
        return this.fieldErrors;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public static ApiErrorResponseBuilder builder() {
        return new ApiErrorResponseBuilder();
    }

    public static class ApiErrorResponseBuilder {
        private Instant timestamp;
        private int status;
        private String error;
        private String message;
        private String path;
        private Map<String, String> fieldErrors;

        public ApiErrorResponseBuilder timestamp(Instant timestamp) {
            this.timestamp = timestamp;
            return this;
        }
        public ApiErrorResponseBuilder status(int status) {
            this.status = status;
            return this;
        }
        public ApiErrorResponseBuilder error(String error) {
            this.error = error;
            return this;
        }
        public ApiErrorResponseBuilder message(String message) {
            this.message = message;
            return this;
        }
        public ApiErrorResponseBuilder path(String path) {
            this.path = path;
            return this;
        }
        public ApiErrorResponseBuilder fieldErrors(Map<String, String> fieldErrors) {
            this.fieldErrors = fieldErrors;
            return this;
        }

        public ApiErrorResponse build() {
            ApiErrorResponse instance = new ApiErrorResponse();
            instance.timestamp = this.timestamp;
            instance.status = this.status;
            instance.error = this.error;
            instance.message = this.message;
            instance.path = this.path;
            instance.fieldErrors = this.fieldErrors;
            return instance;
        }
    }

}
