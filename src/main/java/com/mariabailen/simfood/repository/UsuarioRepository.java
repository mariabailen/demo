package com.mariabailen.simfood.repository;

import org.springframework.data.repository.CrudRepository;

import com.mariabailen.simfood.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    
}
