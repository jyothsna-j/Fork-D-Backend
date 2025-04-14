package com.forkd.forkd_backend.controller.uEngagePojos;

import java.util.List;
import java.util.Objects;

public class CreateTaskRequest {

    private String storeId;
    private OrderDetails order_details;
    private PickupDropDetails pickup_details;
    private PickupDropDetails drop_details;
    private List<OrderItem> order_items;

    // Getters and Setters
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public OrderDetails getOrder_details() {
        return order_details;
    }

    public void setOrder_details(OrderDetails order_details) {
        this.order_details = order_details;
    }

    public PickupDropDetails getPickup_details() {
        return pickup_details;
    }

    public void setPickup_details(PickupDropDetails pickup_details) {
        this.pickup_details = pickup_details;
    }

    public PickupDropDetails getDrop_details() {
        return drop_details;
    }

    public void setDrop_details(PickupDropDetails drop_details) {
        this.drop_details = drop_details;
    }

    public List<OrderItem> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<OrderItem> order_items) {
        this.order_items = order_items;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateTaskRequest)) return false;
        CreateTaskRequest that = (CreateTaskRequest) o;
        return Objects.equals(storeId, that.storeId) &&
                Objects.equals(order_details, that.order_details) &&
                Objects.equals(pickup_details, that.pickup_details) &&
                Objects.equals(drop_details, that.drop_details) &&
                Objects.equals(order_items, that.order_items);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(storeId, order_details, pickup_details, drop_details, order_items);
    }

    // toString
    @Override
    public String toString() {
        return "CreateTaskRequest{" +
                "storeId='" + storeId + '\'' +
                ", order_details=" + order_details +
                ", pickup_details=" + pickup_details +
                ", drop_details=" + drop_details +
                ", order_items=" + order_items +
                '}';
    }

    // --- Nested classes ---

    public static class OrderDetails {
        private int order_total;
        private String paid;
        private String vendor_order_id;
        private String order_source;
        private String customer_orderId;

        // Getters and Setters
        public int getOrder_total() {
            return order_total;
        }

        public void setOrder_total(int order_total) {
            this.order_total = order_total;
        }

        public String getPaid() {
            return paid;
        }

        public void setPaid(String paid) {
            this.paid = paid;
        }

        public String getVendor_order_id() {
            return vendor_order_id;
        }

        public void setVendor_order_id(String vendor_order_id) {
            this.vendor_order_id = vendor_order_id;
        }

        public String getOrder_source() {
            return order_source;
        }

        public void setOrder_source(String order_source) {
            this.order_source = order_source;
        }

        public String getCustomer_orderId() {
            return customer_orderId;
        }

        public void setCustomer_orderId(String customer_orderId) {
            this.customer_orderId = customer_orderId;
        }

        // equals, hashCode, toString
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OrderDetails)) return false;
            OrderDetails that = (OrderDetails) o;
            return order_total == that.order_total &&
                    Objects.equals(paid, that.paid) &&
                    Objects.equals(vendor_order_id, that.vendor_order_id) &&
                    Objects.equals(order_source, that.order_source) &&
                    Objects.equals(customer_orderId, that.customer_orderId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(order_total, paid, vendor_order_id, order_source, customer_orderId);
        }

        @Override
        public String toString() {
            return "OrderDetails{" +
                    "order_total=" + order_total +
                    ", paid='" + paid + '\'' +
                    ", vendor_order_id='" + vendor_order_id + '\'' +
                    ", order_source='" + order_source + '\'' +
                    ", customer_orderId='" + customer_orderId + '\'' +
                    '}';
        }
    }

    public static class PickupDropDetails {
        private String name;
        private String contact_number;
        private double latitude;
        private double longitude;
        private String address;
        private String city;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        // equals, hashCode, toString
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PickupDropDetails)) return false;
            PickupDropDetails that = (PickupDropDetails) o;
            return Double.compare(that.latitude, latitude) == 0 &&
                    Double.compare(that.longitude, longitude) == 0 &&
                    Objects.equals(name, that.name) &&
                    Objects.equals(contact_number, that.contact_number) &&
                    Objects.equals(address, that.address) &&
                    Objects.equals(city, that.city);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, contact_number, latitude, longitude, address, city);
        }

        @Override
        public String toString() {
            return "PickupDropDetails{" +
                    "name='" + name + '\'' +
                    ", contact_number='" + contact_number + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

    public static class OrderItem {
        private String id;
        private String name;
        private int quantity;
        private int price;

        // Getters and Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        // equals, hashCode, toString
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof OrderItem)) return false;
            OrderItem that = (OrderItem) o;
            return quantity == that.quantity &&
                    price == that.price &&
                    Objects.equals(id, that.id) &&
                    Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, quantity, price);
        }

        @Override
        public String toString() {
            return "OrderItem{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    '}';
        }
    }
}
