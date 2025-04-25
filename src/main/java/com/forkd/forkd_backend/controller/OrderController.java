package com.forkd.forkd_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forkd.forkd_backend.controller.uEngagePojos.TrackTaskCallbackRequest.Data;
import com.forkd.forkd_backend.pojos.Order;
import com.forkd.forkd_backend.service.OrderService;
import com.forkd.forkd_backend.utils.ApiResponse;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<ApiResponse<List<Order>>> fetchOrdersByUserId(@PathVariable Long userId){
		List<Order> orders = orderService.getOrdersByUserId(userId);
		
		return orders.isEmpty()?
				ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("Orders not found", List.of()))
				:ResponseEntity.ok()
					.body(new ApiResponse<>("Orders retrieved", orders));
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<ApiResponse<List<Order>>> fetchOrdersByRestaurantId(@PathVariable int restaurantId){
		List<Order> orders = orderService.getOrdersByRestaurantId(restaurantId);
		
		return orders.isEmpty()?
				ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("Orders not found", List.of()))
				:ResponseEntity.ok()
					.body(new ApiResponse<>("Orders retrieved", orders));
	}
	
	@GetMapping("/approve-payment")
	public ResponseEntity<ApiResponse<List<Order>>> getOrdersForApproval(){
		List<Order> orders = orderService.getOrdersToApprove(); 
		
		return orders.isEmpty()?
				ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("Orders not found", List.of()))
				:ResponseEntity.ok()
					.body(new ApiResponse<>("Orders retrieved", orders));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Integer>> createOrder(@RequestBody Order order) {
		int orderId = orderService.createOrder(order);
	    return orderId == -1 ? 
	    		ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        		.body(new ApiResponse<>("Failed to create order", -1))
	        	: ResponseEntity.ok()
	        		.body(new ApiResponse<>("Created order successfully", 1));
	}
	
	@PutMapping("/{id}/update-status/{status}")
	public ResponseEntity<ApiResponse<String>> updateOrderStatus(@PathVariable("id") int id, @PathVariable("status") String status) {
		boolean updated = orderService.updateOrderStatus(id, status);
		return updated ?
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        		.body(new ApiResponse<>("Failed to update the status", null))
        	: ResponseEntity.ok()
        		.body(new ApiResponse<>("Update successful", null));
	}
	
	@GetMapping("/{id}/rider-details")
	public ResponseEntity<ApiResponse<Data>> getRiderDetails(@PathVariable("id") int id){
		Data data = orderService.getRiderDetails(id);
		
		return data==null?
				ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("Rider not assigned", null))
				:ResponseEntity.ok()
					.body(new ApiResponse<>("Rider details retrieved", data));
	}
}
