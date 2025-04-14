package com.forkd.forkd_backend.controller.uEngagePojos;

import java.util.Objects;

public class GetServiceabilityResponse {

    private String status;
    private Serviceability serviceability;
    private Payouts payouts;

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Serviceability getServiceability() {
        return serviceability;
    }

    public void setServiceability(Serviceability serviceability) {
        this.serviceability = serviceability;
    }

    public Payouts getPayouts() {
        return payouts;
    }

    public void setPayouts(Payouts payouts) {
        this.payouts = payouts;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetServiceabilityResponse)) return false;
        GetServiceabilityResponse that = (GetServiceabilityResponse) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(serviceability, that.serviceability) &&
                Objects.equals(payouts, that.payouts);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(status, serviceability, payouts);
    }

    // toString
    @Override
    public String toString() {
        return "GetServiceabilityResponse{" +
                "status='" + status + '\'' +
                ", serviceability=" + serviceability +
                ", payouts=" + payouts +
                '}';
    }

    // Nested class: Serviceability
    public static class Serviceability {
        private boolean riderServiceAble;
        private boolean locationServiceAble;

        // Getters and Setters
        public boolean isRiderServiceAble() {
            return riderServiceAble;
        }

        public void setRiderServiceAble(boolean riderServiceAble) {
            this.riderServiceAble = riderServiceAble;
        }

        public boolean isLocationServiceAble() {
            return locationServiceAble;
        }

        public void setLocationServiceAble(boolean locationServiceAble) {
            this.locationServiceAble = locationServiceAble;
        }

        // equals
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Serviceability)) return false;
            Serviceability that = (Serviceability) o;
            return riderServiceAble == that.riderServiceAble &&
                    locationServiceAble == that.locationServiceAble;
        }

        // hashCode
        @Override
        public int hashCode() {
            return Objects.hash(riderServiceAble, locationServiceAble);
        }

        // toString
        @Override
        public String toString() {
            return "Serviceability{" +
                    "riderServiceAble=" + riderServiceAble +
                    ", locationServiceAble=" + locationServiceAble +
                    '}';
        }
    }

    // Nested class: Payouts
    public static class Payouts {
        private double total;
        private double price;
        private double tax;

        // Getters and Setters
        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getTax() {
            return tax;
        }

        public void setTax(double tax) {
            this.tax = tax;
        }

        // equals
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Payouts)) return false;
            Payouts payouts = (Payouts) o;
            return Double.compare(payouts.total, total) == 0 &&
                    Double.compare(payouts.price, price) == 0 &&
                    Double.compare(payouts.tax, tax) == 0;
        }

        // hashCode
        @Override
        public int hashCode() {
            return Objects.hash(total, price, tax);
        }

        // toString
        @Override
        public String toString() {
            return "Payouts{" +
                    "total=" + total +
                    ", price=" + price +
                    ", tax=" + tax +
                    '}';
        }
    }
}

