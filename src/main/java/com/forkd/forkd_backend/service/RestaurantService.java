package com.forkd.forkd_backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.forkd.forkd_backend.pojos.Address;
import com.forkd.forkd_backend.pojos.Image;
import com.forkd.forkd_backend.pojos.Restaurant;
import com.forkd.forkd_backend.repository.RestaurantRepository;
import com.forkd.forkd_backend.repository.UserRepository;

@Service
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final UserRepository userRepository;
	
	public RestaurantService(RestaurantRepository restaurantRepository, UserRepository userRepository) {
		this.restaurantRepository = restaurantRepository;
		this.userRepository = userRepository;
	}
	
	public List<Restaurant> getAllRestaurants(){
		return restaurantRepository.findAll();
	}
	
	public Restaurant getRestaurantById(int id) {
		return restaurantRepository.findById(id);
	}
	
	public Restaurant getRestaurantByUserId(int id) {
		return restaurantRepository.findByUserId(id);
	}
	
	public Address getRestaurantAddress(int id) {
		return userRepository.getAddressById((long) id);
	}

	public String updateRestaurantImage(int restaurantId, MultipartFile file) {
		try {
			Restaurant newRestaurant = new Restaurant();
			Image image = new Image(file.getBytes());
			image.setImageName(file.getOriginalFilename());
			image.setImageType(file.getContentType());
			
			newRestaurant.setRestaurantId(restaurantId);
			newRestaurant.setLogo(image);
			
			int rows = restaurantRepository.updateRestaurantImage(newRestaurant);
			if (rows > 0) {
	            return "Image updated successfully";
	        } else {
	            return "Restaurant not found or image not updated";
	        }
			
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed to process image";
		}	
	}
	
	public String updateRestaurantCuisine(int restaurantId, Restaurant restaurant) {
		int rows = restaurantRepository.updateRestaurantCuisine(restaurant);
		if (rows > 0) {
		    return "Cuisine updated successfully";
		} else {
		    return "Restaurant not found or cuisine not updated";
		}	
	}
	
	public Image getImage(int id) {
		Restaurant value = restaurantRepository.findById(id);
		return value.getLogo();
	}
}
