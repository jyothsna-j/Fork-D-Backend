package com.forkd.forkd_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forkd.forkd_backend.pojos.User;
import com.forkd.forkd_backend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return authService.authenticate(request.getUsername(), request.getPassword());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User request) {
        return authService.signup(request);
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
