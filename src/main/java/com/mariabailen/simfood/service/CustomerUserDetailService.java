package com.mariabailen.simfood.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mariabailen.simfood.model.Chef;
import com.mariabailen.simfood.repository.ChefRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService{


    @Autowired
    private ChefRepository chefRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Chef user = chefRepository.findById(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(user.getUsername(), user.getPass(), true, true, true, true, getUserAuthority(user.getRole()));
    }

    private List<GrantedAuthority> getUserAuthority(final String userRole) {
    return Collections.singletonList(new SimpleGrantedAuthority(userRole));
}

    
}
