package com.forkd.forkd_backend.controller.uEngagePojos;
import java.util.Objects;

public class CreateTaskResponse {

    private boolean status;
    private String vendor_order_id;
    private String taskId;
    private String message;
    private String Status_code;

    // Getters and Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVendor_order_id() {
        return vendor_order_id;
    }

    public void setVendor_order_id(String vendor_order_id) {
        this.vendor_order_id = vendor_order_id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus_code() {
        return Status_code;
    }

    public void setStatus_code(String status_code) {
        Status_code = status_code;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateTaskResponse)) return false;
        CreateTaskResponse that = (CreateTaskResponse) o;
        return status == that.status &&
                Objects.equals(vendor_order_id, that.vendor_order_id) &&
                Objects.equals(taskId, that.taskId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(Status_code, that.Status_code);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(status, vendor_order_id, taskId, message, Status_code);
    }

    // toString
    @Override
    public String toString() {
        return "CreateTaskResponse{" +
                "status=" + status +
                ", vendor_order_id='" + vendor_order_id + '\'' +
                ", taskId='" + taskId + '\'' +
                ", message='" + message + '\'' +
                ", Status_code='" + Status_code + '\'' +
                '}';
    }
}
