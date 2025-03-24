package com.forkd.forkd_backend.pojos;

import java.util.Objects;

public class Dish {
	private int dishId;
	private String dishName;
	private String category;
	private float price;
	
	public Dish(int dishId, String dishName, String category, float price) {
		this.dishId = dishId;
		this.dishName = dishName;
		this.category = category;
		this.price = price;
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

	@Override
	public int hashCode() {
		return Objects.hash(category, dishId, dishName, price);
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
				&& Objects.equals(dishName, other.dishName)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}

	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", dishName=" + dishName + ", category=" + category + ", price=" + price
				+ "]";
	}
}
