package com.forkd.forkd_backend.pojos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonCreator;
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

	@Autowired
	@JsonCreator
	public Order(
		@JsonProperty("user") User user,
		@JsonProperty("restaurant") Restaurant restaurant,
		@JsonProperty("amount") float amount,
		@JsonProperty("orderStatus") String orderStatus,
		@JsonProperty("orderDate") LocalDateTime orderDate,
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

	public Order(int orderId, User user, Restaurant restaurant, float amount, String orderStatus,
	             LocalDateTime orderDate, List<OrderedItems> items, UUID orderReferenceNumber,
	             Address pickupAddress, Address dropAddress) {
		this.orderId = orderId;
		this.user = user;
		this.restaurant = restaurant;
		this.amount = amount;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.items = items;
		this.orderReferenceNumber = orderReferenceNumber;
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
}

enum Status {
	PAYMENT_APPROVAL_PENDING,
	ORDER_APPROVED,
	INVALID_PAYMENT,
	PREPARING,
	PREPARED,
	IN_TRANSIT,
	DELIVERED
}