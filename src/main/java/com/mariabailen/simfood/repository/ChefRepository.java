package com.mariabailen.simfood.repository;

import org.springframework.data.repository.CrudRepository;

import com.mariabailen.simfood.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, String> {
    
}
