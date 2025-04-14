package com.forkd.forkd_backend.controller.uEngagePojos;

import java.util.Objects;

public class TrackTaskResponse {

    private boolean status;
    private String message;
    private StatusCode status_code;
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

    public StatusCode getStatus_code() {
        return status_code;
    }

    public void setStatus_code(StatusCode status_code) {
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
        if (!(o instanceof TrackTaskResponse)) return false;
        TrackTaskResponse that = (TrackTaskResponse) o;
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
        return "TrackTaskResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", status_code=" + status_code +
                ", data=" + data +
                '}';
    }

    // --- Nested class Data ---
    public static class Data {
        private String vendor_order_id;
        private String rider_name;
        private String rider_contact;
        private String latitude;
        private String longitude;

        // Getters and Setters
        public String getVendor_order_id() {
            return vendor_order_id;
        }

        public void setVendor_order_id(String vendor_order_id) {
            this.vendor_order_id = vendor_order_id;
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

        // equals
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Data)) return false;
            Data data = (Data) o;
            return Objects.equals(vendor_order_id, data.vendor_order_id) &&
                    Objects.equals(rider_name, data.rider_name) &&
                    Objects.equals(rider_contact, data.rider_contact) &&
                    Objects.equals(latitude, data.latitude) &&
                    Objects.equals(longitude, data.longitude);
        }

        // hashCode
        @Override
        public int hashCode() {
            return Objects.hash(vendor_order_id, rider_name, rider_contact, latitude, longitude);
        }

        // toString
        @Override
        public String toString() {
            return "Data{" +
                    "vendor_order_id='" + vendor_order_id + '\'' +
                    ", rider_name='" + rider_name + '\'' +
                    ", rider_contact='" + rider_contact + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    '}';
        }
    }

    // --- Enum for Status Codes ---
    public enum StatusCode {
        ACCEPTED("Order Created Successfully"),
        ALLOTTED("Rider Allotted to pick up the items"),
        ARRIVED("Rider has reached the pickup location"),
        DISPATCHED("Order is picked up by the rider"),
        ARRIVED_CUSTOMER_DOORSTEP("Rider has reached the drop-off location"),
        DELIVERED("Successfully delivered and transaction has concluded"),
        RTO_INIT("RTO is initiated"),
        RTO_COMPLETE("RTO is completed"),
        CANCELLED("Task is cancelled");

        private final String description;

        StatusCode(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
