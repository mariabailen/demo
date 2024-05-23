package com.mariabailen.simfood.repository;

import org.springframework.data.repository.CrudRepository;

import com.mariabailen.simfood.model.User;

public interface UsuarioRepository extends CrudRepository<User, String> {
    
}
