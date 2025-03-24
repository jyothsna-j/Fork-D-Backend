package com.forkd.forkd_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthManagerConfig {
	
	  private final UserDetailsService userDetailsService;
	    private final PasswordEncoder passwordEncoder;

	    public AuthManagerConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
	        this.userDetailsService = userDetailsService;
	        this.passwordEncoder = passwordEncoder;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userDetailsService);
	        provider.setPasswordEncoder(passwordEncoder);
	        return new ProviderManager(provider);
	    }

}
