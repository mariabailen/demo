package com.mariabailen.simfood.repository;

import org.springframework.data.repository.CrudRepository;

import com.mariabailen.simfood.model.Cocinero;

public interface CocineroRepository extends CrudRepository<Cocinero, Long> {
    
}
