package com.forkd.forkd_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.forkd.forkd_backend.pojos.Dish;
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
	
	
}
