package com.mariabailen.simfood.conf;

import java.util.ArrayList;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.mariabailen.simfood.repository.UserRepository;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	/* 
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/").permitAll()
				.anyRequest()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
        
        ArrayList<User> users = new ArrayList<>();
        for (com.mariabailen.simfood.model.User user : userRepository.findAll()) {
            User.withDefaultPasswordEncoder()
				.username(user.getUsername())
				.password(user.getPass())
				.roles(user.getRole())
				.build();
        }

		return new InMemoryUserDetailsManager(users.stream().toArray(User[]::new));
	}
}*/
