package com.forkd.forkd_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.forkd.forkd_backend.pojos.Dish;
import com.forkd.forkd_backend.service.DishService;
import com.forkd.forkd_backend.utils.ApiResponse;

@RestController
@RequestMapping("/restaurant/{restaurantId}/dishes")
public class DishController {
	
	private final DishService dishService;

	public DishController(DishService dishService) {
		this.dishService = dishService;
	}

	@GetMapping()
	public ResponseEntity<ApiResponse<List<Dish>>> getDishesByRestaurantId(@PathVariable int restaurantId) {
		List<Dish> dishes =  dishService.getDishesByRestaurantId(restaurantId);
		
		
		//NOTE - Change this clients ignore body for NO CONTENT
		if(dishes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("No dishes available", List.of()));
		}
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<>("Dishes retrieved successfully", dishes));
	}
	
	@PostMapping("")
	public ResponseEntity<ApiResponse<String>> addDish(@RequestPart Dish dish, @RequestParam("pic") MultipartFile file){
		String message = dishService.addDish(dish, file);
		
		if(message.startsWith("Dish inserted successfully")) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(message, null));
		}
		else if("An unexpected error occurred.".equals(message)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(message, null));
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(message, null));
		}
		
	}
	
	@PutMapping("")
	public ResponseEntity<ApiResponse<String>> updateDish(@RequestPart Dish dish, @RequestPart(value = "pic", required = false)  MultipartFile file){
		String message = dishService.updateDish(dish, file);
		if("SUCCESS".equals(message)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(message, null));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(message, null));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteDish(@PathVariable int id){
		String message = dishService.deleteDish(id);
		if("SUCCESS".equals(message)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(message, null));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(message, null));
		}
	}
	
}
