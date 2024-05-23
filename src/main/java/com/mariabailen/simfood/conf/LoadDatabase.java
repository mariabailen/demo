package com.mariabailen.simfood.conf;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mariabailen.simfood.model.Chef;
import com.mariabailen.simfood.model.Ingredient;
import com.mariabailen.simfood.model.Receipt;
import com.mariabailen.simfood.model.User;
import com.mariabailen.simfood.repository.ChefRepository;
import com.mariabailen.simfood.repository.IngredientRepository;
import com.mariabailen.simfood.repository.ReceiptRepository;
import com.mariabailen.simfood.repository.UserRepository;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ChefRepository chefRepository, ReceiptRepository receiptRepository, IngredientRepository ingredientRepository) {
        return args -> {
            userRepository.save(new User("admin", "123", "admin"));
            userRepository.save(new User("cocinero", "123", "cocinero"));

            Chef juan = chefRepository.save(new Chef( "Juan", "Perez", LocalDate.parse("1980-01-01"), null));   
            Chef maria = chefRepository.save(new Chef( "María", "Gomez", LocalDate.parse("1990-07-15"), null));   
            Chef pedro = chefRepository.save(new Chef( "Pedro", "López", LocalDate.parse("1980-08-02"), null));   

            ArrayList<Ingredient> ingredientsTortilla = new ArrayList<>();
            Receipt tortilla = receiptRepository.save(new Receipt( "Tortilla de patatas", "Receta tradicional española de patatas, huevos y cebolla.", juan, ingredientsTortilla, null));
            ingredientsTortilla.add(new Ingredient("Patatas", "500 g", tortilla));
            ingredientsTortilla.add(new Ingredient("Huevos", "4 u", tortilla));
            ingredientsTortilla.add(new Ingredient("Cebolla", "1 u", tortilla));
            ingredientRepository.saveAll(ingredientsTortilla);


            ArrayList<Ingredient> ingredientsPaella = new ArrayList<>();
            Receipt paella = receiptRepository.save(new Receipt( "Paella valenciana", "Arroz con azafrán, verduras, carne y marisco.", maria, ingredientsPaella, null));
            ingredientsPaella.add(new Ingredient("Arroz", "250 g", paella));
            ingredientsPaella.add(new Ingredient("Azafrán", "0.05 g", paella));
            ingredientRepository.saveAll(ingredientsPaella);

            ArrayList<Ingredient> ingredientsSalmorejo = new ArrayList<>();
            Receipt salmorejo =receiptRepository.save(new Receipt( "Salmorejo cordobés", "Crema fría de tomate, pan, ajo y aceite de oliva.", pedro, ingredientsSalmorejo, null));
            ingredientsSalmorejo.add(new Ingredient("Pan", "250 g", salmorejo));
            ingredientsSalmorejo.add(new Ingredient("Ajo", "2 u", salmorejo));
            ingredientsSalmorejo.add(new Ingredient("Aceite de oliva", "100 g", salmorejo));
            ingredientRepository.saveAll(ingredientsSalmorejo);

            System.out.println("Init database");
        };
    }
}
