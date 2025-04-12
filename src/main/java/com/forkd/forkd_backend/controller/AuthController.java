package com.forkd.forkd_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forkd.forkd_backend.pojos.User;
import com.forkd.forkd_backend.service.AuthService;
import com.forkd.forkd_backend.utils.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody AuthRequest request) {
    	ApiResponse<String> token = authService.authenticate(request.getUsername(), request.getPassword());
    	return token.getData() == null
    			? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(token) : ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@RequestBody User request) {
    	ApiResponse<String> token = authService.signup(request);
    	return token.getData() == null
    			? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(token) : ResponseEntity.ok(token);
    }
}

//Request DTOs
class AuthRequest {
 private String username;
 private String password;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
 
}
