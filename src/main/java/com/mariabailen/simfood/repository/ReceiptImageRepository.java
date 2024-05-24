package com.mariabailen.simfood.repository;

import org.springframework.data.repository.CrudRepository;

import com.mariabailen.simfood.model.ReceiptImage;

public interface ReceiptImageRepository extends CrudRepository<ReceiptImage, Long> {
    
}
