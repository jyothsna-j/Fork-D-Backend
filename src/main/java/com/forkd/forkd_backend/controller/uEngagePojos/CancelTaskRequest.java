package com.forkd.forkd_backend.controller.uEngagePojos;

import java.util.Objects;

public class CancelTaskRequest {

    private String storeId;
    private String taskId;

    // Getters and Setters
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CancelTaskRequest)) return false;
        CancelTaskRequest that = (CancelTaskRequest) o;
        return Objects.equals(storeId, that.storeId) &&
               Objects.equals(taskId, that.taskId);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(storeId, taskId);
    }

    // toString
    @Override
    public String toString() {
        return "CancelTaskRequest{" +
               "storeId='" + storeId + '\'' +
               ", taskId='" + taskId + '\'' +
               '}';
    }
}
