package com.forkd.forkd_backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.forkd.forkd_backend.controller.uEngagePojos.TrackTaskCallbackRequest;
import com.forkd.forkd_backend.controller.uEngagePojos.TrackTaskCallbackRequest.Data;
import com.forkd.forkd_backend.pojos.Order;
import com.forkd.forkd_backend.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public List<Order> getOrders() {
		return orderRepository.getOrders();
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
	
	public Integer createOrder(Order order) {
		order.setOrderReferenceNumber(UUID.randomUUID());
		order.setDeliveryStatus("NOT_ALLOTTED");
		return orderRepository.insertOrder(order);
	}

	public boolean updateOrderStatus(int orderId, String status) {
		return orderRepository.updateStatus(orderId, status);
	}
	
	public void updateOrderStatus(String taskId, String status) {
		orderRepository.updateStatus(taskId, status);
	}
	
	public void updateDeliveryStatus(String taskid, String status) {
		orderRepository.updateDeliveryStatus(taskid, status);
	}

	public void updateTaskId(int orderId, String taskId) {
		orderRepository.updatetaskId(orderId, taskId);
		
	}

	public void updateRiderTaskDetails(TrackTaskCallbackRequest request) {
		orderRepository.updateRiderTaskDetails(request);
	}
	
	public Data getRiderDetails(int orderId) {
		return orderRepository.getRiderDetails(orderId);
	}

}
