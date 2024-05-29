package com.mariabailen.simfood.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariabailen.simfood.model.Chef;
import com.mariabailen.simfood.repository.ChefRepository;

@Service
public class ChefService {

    @Autowired
    ChefRepository chefRepository;

    public Optional<Chef> getChef(String username) {
        return chefRepository.findById(username);
    }

    public List<Chef> getAllChefs() {
        ArrayList<Chef> chefs = new ArrayList<Chef>();
        for (Chef chef : chefRepository.findAll()) {
            chefs.add(chef);
        }
        return chefs;
    }

    public List<Chef> getChefFilteredByNameAndLastName(String input) {
        ArrayList<Chef> chefs = new ArrayList<Chef>();
        for (Chef chef : chefRepository.findAll()) {
            if (chef.getName().toLowerCase().contains(input.toLowerCase())
                    || chef.getLastName().toLowerCase().contains(input.toLowerCase())) {
                chefs.add(chef);
            }
        }
        return chefs;
    }

     public Chef saveChef( String name, String lastName, LocalDate date, String imagePath) {
        Chef chef = new Chef(name, name, lastName, date, imagePath, "123", "chef");
        return chefRepository.save(chef);
     }
}
