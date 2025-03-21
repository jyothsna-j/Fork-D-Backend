package com.forkd.forkd_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forkd.forkd_backend.pojos.Dish;
import com.forkd.forkd_backend.service.DishService;

@RestController
@RequestMapping("/restaurant/{restaurantId}/dishes")
public class DishController {
	
	private final DishService dishService;

	public DishController(DishService dishService) {
		this.dishService = dishService;
	}

	@GetMapping
	public List<Dish> getDishesByRestaurantId(@PathVariable int restaurantId) {
		return dishService.getDishesByRestaurantId(restaurantId);
	}
	
}
