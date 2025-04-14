package com.forkd.forkd_backend.controller.uEngagePojos;

import java.util.Objects;

public class CancelTaskResponse {

    private boolean status;
    private StatusCode status_code;
    private String message;

    // Getters and Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public StatusCode getStatus_code() {
        return status_code;
    }

    public void setStatus_code(StatusCode status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CancelTaskResponse)) return false;
        CancelTaskResponse that = (CancelTaskResponse) o;
        return status == that.status &&
               status_code == that.status_code &&
               Objects.equals(message, that.message);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(status, status_code, message);
    }

    // toString
    @Override
    public String toString() {
        return "CancelTaskResponse{" +
               "status=" + status +
               ", status_code=" + status_code +
               ", message='" + message + '\'' +
               '}';
    }

    // --- Enum for Status Codes ---
    public enum StatusCode {
        CANCELLED("Order has been cancelled");

        private final String description;

        StatusCode(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
