package com.forkd.forkd_backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.forkd.forkd_backend.controller.uEngagePojos.*;
import com.forkd.forkd_backend.controller.uEngagePojos.CreateTaskRequest.OrderDetails;
import com.forkd.forkd_backend.controller.uEngagePojos.CreateTaskRequest.OrderItem;
import com.forkd.forkd_backend.controller.uEngagePojos.CreateTaskRequest.PickupDropDetails;
import com.forkd.forkd_backend.controller.uEngagePojos.TrackTaskCallbackRequest.EventStatus;
import com.forkd.forkd_backend.pojos.Order;
import com.forkd.forkd_backend.pojos.OrderedItems;
import com.forkd.forkd_backend.service.OrderService;
import com.forkd.forkd_backend.utils.StoreIdMapping;

@RestController
@RequestMapping("/uengage")
public class uEngageController {
	
	@Autowired
	RestTemplate restTemplate;

	String externalUrl = "https://riderapi.uengage.in/";
	String token = "UEN680278d272420";
	
	OrderService orderService;
	StoreIdMapping storeIdMapping = new StoreIdMapping();
	
	public uEngageController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/test")
	public ResponseEntity<?> testExternalApi() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        try {
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);

        }  catch (ResourceAccessException ex) {
            System.err.println("Timeout or connection error: " + ex.getMessage());
            return ResponseEntity.status(504).body("External API not responding");

        } catch (Exception ex) {
            System.err.println("Unknown error: " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Unexpected error occurred");
        }
    }

	@PostMapping("/getServiceability")
	public ResponseEntity<GetServiceabilityResponse> GetServiceability(@RequestBody GetServiceabilityRequest payload) {
		String URL = externalUrl + "getServiceability";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("access-token", token);
		
		System.out.println(payload.getStore_id());
		System.out.println(Integer.valueOf(payload.getStore_id()));
		System.out.println(storeIdMapping.getTargetId(Integer.valueOf(payload.getStore_id())));
		
		String storeId = storeIdMapping.getTargetId(Integer.valueOf(payload.getStore_id()));
		payload.setStore_id(storeId);
		
		System.out.print("Store Id set: " + payload.getStore_id());
		
		HttpEntity<GetServiceabilityRequest> requestEntity = new HttpEntity<GetServiceabilityRequest>(payload, headers);
		
		try {
			System.out.println(payload);
			ResponseEntity<GetServiceabilityResponse> response = restTemplate.postForEntity(URL, requestEntity, GetServiceabilityResponse.class);
			System.out.println(response);
			System.out.println(ResponseEntity.ok(response.getBody()));
		    return ResponseEntity.ok(response.getBody());
		}
		catch (ResourceAccessException ex) {
		    // Timeout or connection error
		    System.err.println("Timeout or connection error: " + ex.getMessage());
		    return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(null);
		}
	}
	
	@PostMapping("/createTask")
	public ResponseEntity<CreateTaskResponse> createTask(@RequestBody Order order) {
		
		System.out.println(order);
		
		CreateTaskRequest request = new CreateTaskRequest();
		request.setStoreId(storeIdMapping.getTargetId(order.getRestaurant().getRestaurantId()));
		
		OrderDetails ordDets = new OrderDetails();
		ordDets.setOrder_total(order.getAmount());
		ordDets.setPaid("true");
		ordDets.setVendor_order_id(String.valueOf(order.getRestaurant().getRestaurantId()));
		ordDets.setOrder_source("website");
		request.setOrder_details(ordDets);

		PickupDropDetails pickup = new PickupDropDetails();
		pickup.setName(order.getRestaurant().getRestaurantName());
		pickup.setContact_number(order.getPickupAddress().getContactNumber().toString());
		pickup.setLatitude(order.getPickupAddress().getLatitude());
		pickup.setLongitude(order.getPickupAddress().getLongitude());
		pickup.setAddress(order.getPickupAddress().getAddress());
		pickup.setCity("Noida");
		request.setPickup_details(pickup);
		
		PickupDropDetails drop = new PickupDropDetails();
		drop.setName(order.getUser().getName());
		drop.setContact_number(order.getUser().getContactNumber().toString());
		drop.setLatitude(order.getDropAddress().getLatitude());
		drop.setLongitude(order.getDropAddress().getLongitude());
		drop.setAddress(order.getDropAddress().getAddress());
		drop.setCity("Noida");
		request.setDrop_details(drop);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		for(OrderedItems orderItem : order.getItems()) {
			OrderItem item = new OrderItem();
			item.setId(String.valueOf(orderItem.getOrderedItemId()));
			item.setName(orderItem.getDish().getDishName());
			item.setQuantity(orderItem.getQuantity());
			item.setPrice(orderItem.getPrice());
			items.add(item);
		}
		request.setOrder_items(items);
		System.out.println(request);
		
		String URL = externalUrl + "createTask";
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("access-token", token);

		HttpEntity<CreateTaskRequest> requestEntity = new HttpEntity<CreateTaskRequest>(request, headers);
		ResponseEntity<CreateTaskResponse> response = restTemplate.postForEntity(URL, requestEntity, CreateTaskResponse.class);
		
		System.out.println(response.getBody());
		if(response.getBody().getStatus_code().equals("ACCEPTED")){
			orderService.updateOrderStatus(order.getOrderId(), "ORDER_APPROVED");
			orderService.updateTaskId(order.getOrderId(), response.getBody().getTaskId());
			orderService.updateDeliveryStatus(response.getBody().getTaskId(), response.getBody().getStatus_code());
		}
		
		return ResponseEntity.ok(response.getBody());
	}
	
	@PostMapping("/updateStatus")
	public ResponseEntity<TrackTaskCallbackResponse> updateStatus(@RequestBody TrackTaskCallbackRequest request){
		orderService.updateDeliveryStatus(request.getData().getTaskId(), request.getStatus_code().toString());
		if(request.getStatus_code()==EventStatus.ALLOTTED) {
			orderService.updateRiderTaskDetails(request);
		}
		if(request.getStatus_code()==EventStatus.DELIVERED) {
			orderService.updateOrderStatus(request.getData().getTaskId(), "DELIVERED");
		}
		orderService.updateDeliveryStatus(request.getData().getTaskId(), request.getStatus_code().toString());
		TrackTaskCallbackResponse response = new TrackTaskCallbackResponse(true, "Webhook Processed");
		return ResponseEntity.ok(response);
	}
} 