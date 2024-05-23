package com.mariabailen.simfood.repository;

import org.springframework.data.repository.CrudRepository;

import com.mariabailen.simfood.model.Receipt;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    
}
