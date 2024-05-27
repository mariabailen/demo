package com.mariabailen.simfood.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariabailen.simfood.model.Chef;
import com.mariabailen.simfood.repository.ChefRepository;

@Service
public class ChefService {
    
    @Autowired
    ChefRepository chefRepository;

    public Optional<Chef> getChef(Long id) {
        return chefRepository.findById(id);
    }
}
