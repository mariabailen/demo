package com.mariabailen.simfood.repository;

import org.springframework.data.repository.CrudRepository;

import com.mariabailen.simfood.model.Receta;

public interface RecetaRepository extends CrudRepository<Receta, Long> {
    
}
