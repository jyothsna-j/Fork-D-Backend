package com.forkd.forkd_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forkd.forkd_backend.pojos.User;
import com.forkd.forkd_backend.service.AuthService;
import com.forkd.forkd_backend.service.MasterService;
import com.forkd.forkd_backend.utils.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	 private final MasterService masterService;

    public AuthController(AuthService authService, MasterService masterService) {
        this.authService = authService;
        this.masterService = masterService;
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
    
    @GetMapping("/users")
	public ResponseEntity<ApiResponse<List<User>>> getAllUsers(){
		List<User> users = this.authService.getAllUsers();
		
		return users.isEmpty()?
				ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ApiResponse<>("Restaurants not found", List.of()))
				:ResponseEntity.ok()
					.body(new ApiResponse<>("Restaurants retrieved", users));
	}
    
    @GetMapping("/status")
    public ApiResponse<Boolean> getStatus() {
        boolean status = masterService.getCurrentState();
        return new ApiResponse<>("Fetched status", status);
    }

    // POST new value (true/false)
    @PostMapping("/status")
    public ApiResponse<Boolean> updateStatus(@RequestBody Boolean newValue) {
        masterService.setState(newValue);
        return new ApiResponse<>("Status updated", newValue);
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
