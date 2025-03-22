package com.forkd.forkd_backend.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.forkd.forkd_backend.pojos.Image;
import com.forkd.forkd_backend.pojos.Restaurant;
import com.forkd.forkd_backend.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	private final RestaurantService restaurantService;

	public RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	@GetMapping
	public List<Restaurant> getAllRestaurants(){
		return this.restaurantService.getAllRestaurants();
	}
	
	@GetMapping("/{id}")
	public Restaurant getRestaurantById(@PathVariable int id) {
		return restaurantService.getRestaurantById(id);
	}
	
	@PutMapping("")
	public String updateRestaurant(@RequestPart Restaurant restaurant, @RequestParam("logo") MultipartFile file) {
		System.out.println("hereee");
		restaurantService.updateRestaurant(restaurant, file);
		return "updated";
	}
	
	@PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            this.restaurantService.saveImage(file);
            System.out.println("here1");
            return "File uploaded successfully";
        } catch (Exception e) {
            System.out.println("Not here I hope");
            return "Upload failed";
        }
    }
	
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable int id) {
	    Image image = this.restaurantService.getImage(id);
	    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getData());
	}
}
