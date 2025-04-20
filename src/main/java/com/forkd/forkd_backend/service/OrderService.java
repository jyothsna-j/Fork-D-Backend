package com.forkd.forkd_backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.forkd.forkd_backend.controller.uEngagePojos.TrackTaskCallbackRequest;
import com.forkd.forkd_backend.pojos.Order;
import com.forkd.forkd_backend.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<Order> getOrdersByUserId(Long userId) {
		return orderRepository.getOrdersByUserId(userId);
	}
	
	public List<Order> getOrdersByRestaurantId(int restaurantId) {
		return orderRepository.getOrdersByRestaurantId(restaurantId);
	}
	
	public List<Order> getOrdersToApprove(){
		return orderRepository.getPaymentApprovalPendingOrders();
	}
	
	public void createOrder(Order order) {
		order.setOrderReferenceNumber(UUID.randomUUID());
		orderRepository.insertOrder(order);
	}

	public void updateOrderStatus(int id, String status) {
		orderRepository.updateStatus(id, status);
	}
	
	public void updateOrderStatus(String id, String status) {
		orderRepository.updateStatus(id, status);
	}
	
	public void updateDeliveryStatus(String id, String status) {
		orderRepository.updateDeliveryStatus(id, status);
	}

	public void updateTaskId(int orderId, String taskId) {
		orderRepository.updatetaskId(orderId, taskId);
		
	}

	public void updateRiderTaskDetails(TrackTaskCallbackRequest request) {
		orderRepository.updateRiderTaskDetails(request);
	}

}
