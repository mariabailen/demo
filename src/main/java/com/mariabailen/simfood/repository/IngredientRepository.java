package com.mariabailen.simfood.repository;

import org.springframework.data.repository.CrudRepository;

import com.mariabailen.simfood.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{

    
} 
