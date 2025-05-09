package com.forkd.forkd_backend.pojos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

	private int orderId;
	private User user;
	private Restaurant restaurant;
	private float amount;
	private String orderStatus;
	private String deliveryStatus;
	private LocalDateTime orderDate;
	private List<OrderedItems> items;
	private UUID orderReferenceNumber;
	private Address pickupAddress;
	private Address dropAddress;

	@JsonCreator
	public Order(
		@JsonProperty("user") User user,
		@JsonProperty("restaurant") Restaurant restaurant,
		@JsonProperty("amount") float amount,
		@JsonProperty("orderStatus") String orderStatus,
		@JsonProperty("orderDate")@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime orderDate,
		@JsonProperty("items") List<OrderedItems> items,
		@JsonProperty("pickupAddress") Address pickupAddress,
		@JsonProperty("dropAddress") Address dropAddress
	) {
		this.user = user;
		this.restaurant = restaurant;
		this.amount = amount;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.items = items;
		this.pickupAddress = pickupAddress;
		this.dropAddress = dropAddress;
	}

	public Order() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderedItems> getItems() {
		return items;
	}

	public void setItems(List<OrderedItems> items) {
		this.items = items;
	}

	public UUID getOrderReferenceNumber() {
		return orderReferenceNumber;
	}

	public void setOrderReferenceNumber(UUID orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}

	public Address getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(Address pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public Address getDropAddress() {
		return dropAddress;
	}

	public void setDropAddress(Address dropAddress) {
		this.dropAddress = dropAddress;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, deliveryStatus, dropAddress, items, orderDate, orderId, orderReferenceNumber,
				orderStatus, pickupAddress, restaurant, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount)
				&& Objects.equals(deliveryStatus, other.deliveryStatus)
				&& Objects.equals(dropAddress, other.dropAddress) && Objects.equals(items, other.items)
				&& Objects.equals(orderDate, other.orderDate) && orderId == other.orderId
				&& Objects.equals(orderReferenceNumber, other.orderReferenceNumber)
				&& Objects.equals(orderStatus, other.orderStatus) && Objects.equals(pickupAddress, other.pickupAddress)
				&& Objects.equals(restaurant, other.restaurant) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user=" + user + ", restaurant=" + restaurant + ", amount=" + amount
				+ ", orderStatus=" + orderStatus + ", deliveryStatus=" + deliveryStatus + ", orderDate=" + orderDate
				+ ", items=" + items + ", orderReferenceNumber=" + orderReferenceNumber + ", pickupAddress="
				+ pickupAddress + ", dropAddress=" + dropAddress + "]";
	}
	
	
}

enum Status {
	PAYMENT_APPROVAL_PENDING,
	ORDER_APPROVED,
	INVALID_PAYMENT,
	PENDING,
	PREPARING,
	PREPARED,
	OUT_FOR_DELIVERY,
	DELIVERED
}