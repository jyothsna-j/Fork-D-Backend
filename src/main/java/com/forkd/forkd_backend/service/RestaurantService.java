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
	private Image image = new Image();
	
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
		try {
			image = new Image(file.getBytes());
			System.out.println(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Image getImage() {
		return image;
	}
}
