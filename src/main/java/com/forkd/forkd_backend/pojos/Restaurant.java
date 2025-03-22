package com.forkd.forkd_backend.pojos;

import java.util.Objects;

public class Restaurant {
	private int restaurantId;
	private String restaurantName;
	private String cuisine;
	private Image logo;
	private Image background;
	
	public Restaurant(int restaurantId, String restaurantName, String cuisine, Image logo) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.cuisine = cuisine;
		this.logo = logo;
	}

	public Restaurant() {
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cuisine, restaurantId, restaurantName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		return Objects.equals(cuisine, other.cuisine) && restaurantId == other.restaurantId
				&& Objects.equals(restaurantName, other.restaurantName);
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", cuisine="
				+ cuisine + "]";
	}

	
	
}