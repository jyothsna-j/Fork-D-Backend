package com.forkd.forkd_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forkd.forkd_backend.pojos.Order;
import com.forkd.forkd_backend.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("user/{userId}")
	public List<Order> fetchOrdersByUserId(@PathVariable Long userId){
		return orderService.getOrdersByUserId(userId);
	}
	
	@GetMapping("restaurant/{restaurantId}")
	public List<Order> fetchOrdersByRestaurantId(@PathVariable int restaurantId){
		return orderService.getOrdersByRestaurantId(restaurantId);
	}
	
	@GetMapping("/approve-payment")
	public List<Order> getOrdersForApproval(){
		return orderService.getOrdersToApprove(); 
	}
	
	@PostMapping
	public String createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
		return "placed";
	}
	
	@PutMapping("{id}/update-status/{status}")
	public String updateOrderStatus(@PathVariable("id") int id, @PathVariable("status") String status) {
		orderService.updateOrderStatus(id, status);
		return "update complete";
	}
}
