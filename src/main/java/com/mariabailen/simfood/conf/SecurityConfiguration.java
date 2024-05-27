package com.mariabailen.simfood.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.mariabailen.simfood.model.User;
import com.mariabailen.simfood.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  UserRepository userRepository;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/", "/home").permitAll()
            .anyRequest().permitAll())
        .formLogin((form) -> form
            .loginPage("/login")
            .permitAll())
        .logout((logout) -> logout.permitAll().logoutSuccessUrl("/home"));

    return http.build();
  }


  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    return new InMemoryUserDetailsManager(
        org.springframework.security.core.userdetails.User.withUsername("admin")
            .password("123").roles("ADMIN").build(),
        org.springframework.security.core.userdetails.User.withUsername("chef")
            .password("123").roles("CHEF").build());
  }

}