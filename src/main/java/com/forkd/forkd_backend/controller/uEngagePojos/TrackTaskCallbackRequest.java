package com.forkd.forkd_backend.controller.uEngagePojos;

import java.util.Objects;

public class TrackTaskCallbackRequest {

    private boolean status;
    private String message;
    private EventStatus status_code;
    private Data data;

    // Getters and Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventStatus getStatus_code() {
        return status_code;
    }

    public void setStatus_code(EventStatus status_code) {
        this.status_code = status_code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackTaskCallbackRequest)) return false;
        TrackTaskCallbackRequest that = (TrackTaskCallbackRequest) o;
        return status == that.status &&
               Objects.equals(message, that.message) &&
               status_code == that.status_code &&
               Objects.equals(data, that.data);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(status, message, status_code, data);
    }

    // toString
    @Override
    public String toString() {
        return "TrackTaskCallbackRequest{" +
               "status=" + status +
               ", message='" + message + '\'' +
               ", status_code=" + status_code +
               ", data=" + data +
               '}';
    }

    // --- Enum for Event Status ---
    public enum EventStatus {
        ACCEPTED("Order Created Successfully."),
        ALLOTTED("Rider Allotted to pick up the items."),
        ARRIVED("Rider has reached the pickup location."),
        DISPATCHED("Order is picked up by the rider."),
        ARRIVED_CUSTOMER_DOORSTEP("Rider has reached the drop-off location."),
        DELIVERED("Successfully delivered and transaction has concluded."),
        RTO_INIT("RTO is initiated"),
        RTO_COMPLETE("RTO is completed"),
        CANCELLED("Order is cancelled");

        private final String description;

        EventStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // --- Nested Data class ---
    public static class Data {
        private String taskId;
        private String rider_name;
        private String rider_contact;
        private String latitude;
        private String longitude;
        private String tracking_url;

        // Getters and Setters
        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getRider_name() {
            return rider_name;
        }

        public void setRider_name(String rider_name) {
            this.rider_name = rider_name;
        }

        public String getRider_contact() {
            return rider_contact;
        }

        public void setRider_contact(String rider_contact) {
            this.rider_contact = rider_contact;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getTracking_url() {
            return tracking_url;
        }

        public void setTracking_url(String tracking_url) {
            this.tracking_url = tracking_url;
        }

        // equals, hashCode, toString
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Data)) return false;
            Data data = (Data) o;
            return Objects.equals(taskId, data.taskId) &&
                   Objects.equals(rider_name, data.rider_name) &&
                   Objects.equals(rider_contact, data.rider_contact) &&
                   Objects.equals(latitude, data.latitude) &&
                   Objects.equals(longitude, data.longitude) &&
                   Objects.equals(tracking_url, data.tracking_url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(taskId, rider_name, rider_contact, latitude, longitude, tracking_url);
        }

        @Override
        public String toString() {
            return "Data{" +
                   "taskId='" + taskId + '\'' +
                   ", rider_name='" + rider_name + '\'' +
                   ", rider_contact='" + rider_contact + '\'' +
                   ", latitude='" + latitude + '\'' +
                   ", longitude='" + longitude + '\'' +
                   ", tracking_url='" + tracking_url + '\'' +
                   '}';
        }
    }
}
