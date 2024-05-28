package com.mariabailen.simfood.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariabailen.simfood.model.Ingredient;
import com.mariabailen.simfood.model.Receipt;
import com.mariabailen.simfood.repository.IngredientRepository;
import com.mariabailen.simfood.repository.ReceiptRepository;

@Service
public class ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public List<Receipt> getReceipts() {
        List<Receipt> receipts = new ArrayList<Receipt>();

        for (Receipt receipt : receiptRepository.findAll()) {
            receipts.add(receipt);
        }

        return receipts;
    }

    public List<Receipt> getReceiptsByNameOrDesc(String input) {
        List<Receipt> receipts = new ArrayList<Receipt>();
        for (Receipt receipt : receiptRepository.findAll()) {
            if (receipt.getName().toLowerCase().contains(input.toLowerCase()) || receipt.getDescription().toLowerCase().contains(input.toLowerCase())) {
                receipts.add(receipt);
            }
        }
        return receipts;
    }

    public Optional<Receipt> getReceipt(Long id) {
        return receiptRepository.findById(id);
    }

    public Optional<Receipt> addIngredient(Long id, String name , String description) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        if (receipt.isPresent()) {
            ingredientRepository.save(new Ingredient(name, description, receipt.get()));
        }
        return receiptRepository.findById(id);
    }

}
