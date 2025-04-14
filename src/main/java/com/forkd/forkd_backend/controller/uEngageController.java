package com.forkd.forkd_backend.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.forkd.forkd_backend.controller.uEngagePojos.GetServiceabilityRequest;
import com.forkd.forkd_backend.controller.uEngagePojos.GetServiceabilityResponse;

@RestController
@RequestMapping("/uengage")
public class uEngageController {
	
	String externalUrl = "https://riderapi-staging.uengage.in/";
	String token = "grdgedhs";

	@PostMapping("/getServiceability")
	public ResponseEntity<GetServiceabilityResponse> GetServiceability(@RequestBody GetServiceabilityRequest payload) {
		
		String URL = externalUrl + "getServiceability";
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("access-token", token);

		HttpEntity<GetServiceabilityRequest> requestEntity = new HttpEntity<GetServiceabilityRequest>(payload, headers);
		ResponseEntity<GetServiceabilityResponse> response = restTemplate.postForEntity(URL, requestEntity, GetServiceabilityResponse.class);
		
		return response;
	}
} 