package com.forkd.forkd_backend.config;

import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forkd.forkd_backend.utils.ApiResponse;
import com.forkd.forkd_backend.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	@Lazy
	private UserDetailsService userDetailsService;

	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 
		 JwtAuthFilter jwtFilter = new JwtAuthFilter(jwtUtil, userDetailsService);
		 
		 http
		 	.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		 	.csrf(csrf -> csrf.disable())  // Disable CSRF for testing (enable in production)
		 	.authorizeHttpRequests(auth -> auth
	                .requestMatchers("/auth/**", "/uengage/**").permitAll()
	                .requestMatchers(HttpMethod.GET, "/restaurants/**", "/restaurant/**").permitAll()
	                .anyRequest().authenticated()
	        )
		 	.exceptionHandling(exception -> exception
		            .authenticationEntryPoint((request, response, authException) -> {
		                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		                response.setContentType("application/json");
		                ApiResponse<?> apiResponse = new ApiResponse<>("Login expired, please login again", null);
		                OutputStream out = response.getOutputStream();
		                new ObjectMapper().writeValue(out, apiResponse);
		                out.flush();
		            })
		            .accessDeniedHandler((request, response, accessDeniedException) -> {
		                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		                response.setContentType("application/json");
		                ApiResponse<?> apiResponse = new ApiResponse<>("You do not have permission to access this resource.", null);
		                OutputStream out = response.getOutputStream();
		                new ObjectMapper().writeValue(out, apiResponse);
		                out.flush();
		            })
		        )
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	 }
	 
	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
	     CorsConfiguration config = new CorsConfiguration();
	     config.setAllowedOrigins(List.of("https://fork-d.netlify.app", "http://localhost:4200"));
	     config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	     config.setAllowedHeaders(List.of("*"));
	     config.setAllowCredentials(true);

	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	     source.registerCorsConfiguration("/**", config);
	     return source;
	 }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}