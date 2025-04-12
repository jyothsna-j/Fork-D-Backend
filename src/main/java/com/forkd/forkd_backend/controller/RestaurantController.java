package com.forkd.forkd_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.forkd.forkd_backend.pojos.Image;
import com.forkd.forkd_backend.pojos.Restaurant;
import com.forkd.forkd_backend.service.RestaurantService;
import com.forkd.forkd_backend.utils.ApiResponse;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	private final RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Restaurant>>> getAllRestaurants(){
		List<Restaurant> restaurants = this.restaurantService.getAllRestaurants();
		
		return restaurants.isEmpty()?
				ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("Restaurants not found", List.of()))
				:ResponseEntity.ok()
					.body(new ApiResponse<>("Restaurants retrieved", restaurants));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Restaurant>> getRestaurantById(@PathVariable int id) {
		Restaurant restaurant = restaurantService.getRestaurantById(id);
		return restaurant == null?
				ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("Restaurant not found", null))
				:ResponseEntity.ok()
					.body(new ApiResponse<>("Restaurant retrieved", restaurant));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<ApiResponse<Restaurant>> getRestaurantByUserId(@PathVariable int id) {
		Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
		return restaurant == null?
				ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("Restaurant not found", null))
				:ResponseEntity.ok()
					.body(new ApiResponse<>("Restaurant retrieved", restaurant));
	}
	

	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable int id) {
	    Image image = this.restaurantService.getImage(id);
	    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getData());
	}
	
	
	@PutMapping("/image/{id}")
	public ResponseEntity<ApiResponse<String>> updateRestaurantImage(@PathVariable int id, @RequestParam("logo") MultipartFile file) {
		String message = restaurantService.updateRestaurantImage(id, file);
		if(null == message) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(message, null));
                } else return switch (message) {
                    case "Image updated successfully" -> ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(message, null));
                    case "Failed to process image" -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(message, null));
                    default -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(message, null));
                };
	}
	
	@PutMapping("/cuisine/{id}")
	public  ResponseEntity<ApiResponse<String>> updateCuisines(@PathVariable int id, @RequestBody Restaurant restaurant){
		String message = restaurantService.updateRestaurantCuisine(id, restaurant);
		
		ApiResponse<String> response = new ApiResponse<>(message, null);
		
		if("Cuisine updated successfully".equals(message)) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
}
