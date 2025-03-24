package com.forkd.forkd_backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.forkd.forkd_backend.pojos.Image;
import com.forkd.forkd_backend.pojos.Restaurant;
import com.forkd.forkd_backend.repository.RestaurantRepository;

@Service
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;
	
	public RestaurantService(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}
	
	public List<Restaurant> getAllRestaurants(){
		return restaurantRepository.findAll();
	}
	
	public Restaurant getRestaurantById(int id) {
		return restaurantRepository.findById(id);
	}

	public void saveImage(MultipartFile file) {
		
	}

	public Image getImage(int id) {
		Restaurant value = restaurantRepository.findById(id);
		return value.getLogo();
	}

	public void updateRestaurant(Restaurant restaurant, MultipartFile file) {
		try {
			Restaurant newRestaurant = new Restaurant();
			Image image = new Image(file.getBytes());
			image.setImageName(file.getOriginalFilename());
			image.setImageType(file.getContentType());
			
			newRestaurant.setRestaurantId(restaurant.getRestaurantId());
			newRestaurant.setLogo(image);
			
			restaurantRepository.updateRestaurant(newRestaurant);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
