package com.forkd.forkd_backend.pojos;

import java.util.Objects;

public class Dish {
	private int dishId;
	private int restaurant_id;
	private String dishName;
	private String category;
	private float price;
	private Image dishImage;

	public Dish(int dishId, int restaurant_id, String dishName, String category, float price, Image dishImage) {
		super();
		this.dishId = dishId;
		this.restaurant_id = restaurant_id;
		this.dishName = dishName;
		this.category = category;
		this.price = price;
		this.dishImage = dishImage;
	}

	public Dish() {
		// TODO Auto-generated constructor stub
	}

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Image getDishImage() {
		return dishImage;
	}

	public void setDishImage(Image dishImage) {
		this.dishImage = dishImage;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, dishId, dishImage, dishName, price, restaurant_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dish other = (Dish) obj;
		return Objects.equals(category, other.category) && dishId == other.dishId
				&& Objects.equals(dishImage, other.dishImage) && Objects.equals(dishName, other.dishName)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& restaurant_id == other.restaurant_id;
	}

	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", restaurant_id=" + restaurant_id + ", dishName=" + dishName + ", category="
				+ category + ", price=" + price + ", dishImage=" + dishImage + "]";
	}
	
	
	
}
