package com.forkd.forkd_backend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.forkd.forkd_backend.pojos.User;
import com.forkd.forkd_backend.repository.UserRepository;
import com.forkd.forkd_backend.utils.ApiResponse;
import com.forkd.forkd_backend.utils.JwtUtil;

@Service
public class AuthService implements UserDetailsService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthService(JwtUtil jwtUtil, @Lazy AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public ApiResponse<String> authenticate(String username, String password) {
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userRepository.getUserByUsername(username);
            String token = jwtUtil.generateToken(user.getUserId().toString(), username, user.getRole());
            return new ApiResponse<>("Login successful", token);
    	}
        catch (Exception e){
        	return new ApiResponse<>("Username or password invalid!", null);
        }
    }

    public ApiResponse<String> signup(User newUser) {
        if (userRepository.doesUsernameExist(newUser.getUsername())) {
        	return new ApiResponse<>("Username already exists", null);
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        int userId = userRepository.createUser(newUser);
        String token = jwtUtil.generateToken(Integer.toString(userId), newUser.getUsername(), newUser.getRole());
        return new ApiResponse<>("Signup successful", token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found");
    }
    
    public List<User> getAllUsers(){
    	return userRepository.getAllUsers();
    }
}
