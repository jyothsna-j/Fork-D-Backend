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
	private LocalDateTime orderDate;
	private List<OrderedItems> items;
	private UUID orderReferenceNumber;
	
	@Autowired
	@JsonCreator
	public Order(
			 @JsonProperty("user")User user, 
			 @JsonProperty("restaurant")Restaurant restaurant, 
			 @JsonProperty("amount")float amount, 
			 @JsonProperty("orderStatus")String orderStatus, 
			 @JsonProperty("orderDate")LocalDateTime orderDate,
			 @JsonProperty("items")List<OrderedItems> items) {
		super();
		this.user = user;
		this.restaurant = restaurant;
		this.amount = amount;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.items = items;
	}

	public Order(int orderId, User user, Restaurant restaurant, float amount, String orderStatus, LocalDateTime orderDate,
			List<OrderedItems> items, UUID orderReferenceNumber) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.restaurant = restaurant;
		this.amount = amount;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.items = items;
		this.orderReferenceNumber = orderReferenceNumber;
	}

	public Order() {
		// TODO Auto-generated constructor stub
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
	
}

enum Status {
	  PAYMENT_APPROVAL_PENDING,
	  PAYMENT_APPROVED,
	  INVALID_PAYMENT,
	  PREPARING,
	  PREPARED,
	  IN_TRANSIT,
	  DELIVERED
}