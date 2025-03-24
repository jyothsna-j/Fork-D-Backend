package com.forkd.forkd_backend.pojos;

import java.util.UUID;

public class OrderedItems {
	private int orderedItemId;
	private Dish dish;
	private float price;
	private int quantity;
	private UUID orderReferenceNumber;
	
	public OrderedItems(int orderedItemId, Dish dish, float price, int quantity, UUID orderReferenceNumber) {
		super();
		this.orderedItemId = orderedItemId;
		this.dish = dish;
		this.price = price;
		this.quantity = quantity;
		this.orderReferenceNumber = orderReferenceNumber;
	}

	public OrderedItems() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderedItemId() {
		return orderedItemId;
	}

	public void setOrderedItemId(int orderedItemId) {
		this.orderedItemId = orderedItemId;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public UUID getOrderReferenceNumber() {
		return orderReferenceNumber;
	}

	public void setOrderReferenceNumber(UUID orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}
	
	
	
}
