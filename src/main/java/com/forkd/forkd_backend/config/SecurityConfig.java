package com.forkd.forkd_backend.config;

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

import com.forkd.forkd_backend.utils.JwtUtil;

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