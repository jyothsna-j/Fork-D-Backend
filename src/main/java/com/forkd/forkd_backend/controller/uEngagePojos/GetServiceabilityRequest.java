package com.forkd.forkd_backend.controller.uEngagePojos;

import java.util.Objects;

public class GetServiceabilityRequest {

    private String store_id;
    private Location pickupDetails;
    private Location dropDetails;

    // Getters and Setters
    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public Location getPickupDetails() {
        return pickupDetails;
    }

    public void setPickupDetails(Location pickupDetails) {
        this.pickupDetails = pickupDetails;
    }

    public Location getDropDetails() {
        return dropDetails;
    }

    public void setDropDetails(Location dropDetails) {
        this.dropDetails = dropDetails;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetServiceabilityRequest)) return false;
        GetServiceabilityRequest that = (GetServiceabilityRequest) o;
        return Objects.equals(store_id, that.store_id) &&
                Objects.equals(pickupDetails, that.pickupDetails) &&
                Objects.equals(dropDetails, that.dropDetails);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(store_id, pickupDetails, dropDetails);
    }

    // toString
    @Override
    public String toString() {
        return "GetServiceabilityRequest{" +
                "store_id='" + store_id + '\'' +
                ", pickupDetails=" + pickupDetails +
                ", dropDetails=" + dropDetails +
                '}';
    }

    // Nested static class for Location
    public static class Location {
        private String latitude;
        private String longitude;

        // Getters and Setters
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
            if (!(o instanceof Location)) return false;
            Location location = (Location) o;
            return Objects.equals(latitude, location.latitude) &&
                    Objects.equals(longitude, location.longitude);
        }

        // hashCode
        @Override
        public int hashCode() {
            return Objects.hash(latitude, longitude);
        }

        // toString
        @Override
        public String toString() {
            return "Location{" +
                    "latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    '}';
        }
    }
}
