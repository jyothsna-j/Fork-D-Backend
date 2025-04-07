package com.forkd.forkd_backend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.forkd.forkd_backend.pojos.Dish;
import com.forkd.forkd_backend.pojos.Image;
import com.forkd.forkd_backend.repository.DishRepository;

@Service
public class DishService {
	
	private DishRepository dishRepository;

	public DishService(DishRepository dishRepository) {
		this.dishRepository = dishRepository;
	}
	
	public List<Dish> getDishesByRestaurantId(int restaurantId){
		return dishRepository.findDishesByRestaurant(restaurantId);
	}
	
	public String addDish(Dish dish, MultipartFile file){
		
		try {
			
			Image image = new Image(file.getContentType(), file.getOriginalFilename(), file.getBytes());
			dish.setDishImage(image);
			return dishRepository.insertDish(dish);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return "FAILURE";
			
		}
	}
	
	public String updateDish(Dish dish, MultipartFile file) {
		try {
			if(file!=null) {
				Image image = new Image(file.getContentType(), file.getOriginalFilename(), file.getBytes());
				dish.setDishImage(image);
			}
			return dishRepository.updateDish(dish)==0 ? "FAILURE" : "SUCCESS";
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return "FAILURE";
			
		}
		
	}
	
	public String deleteDish(int dishId) {
		return dishRepository.deleteDish(dishId)==0 ? "FAILURE" : "SUCCESS";
	}
	
	
}
