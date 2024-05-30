package com.mariabailen.simfood.conf;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mariabailen.simfood.model.Chef;
import com.mariabailen.simfood.model.Ingredient;
import com.mariabailen.simfood.model.Receipt;
import com.mariabailen.simfood.model.ReceiptImage;
import com.mariabailen.simfood.repository.ChefRepository;
import com.mariabailen.simfood.repository.IngredientRepository;
import com.mariabailen.simfood.repository.ReceiptImageRepository;
import com.mariabailen.simfood.repository.ReceiptRepository;

@Configuration
public class LoadDatabase {

        @Bean
        CommandLineRunner initDatabase(ChefRepository chefRepository,
                        ReceiptRepository receiptRepository, IngredientRepository ingredientRepository,
                        ReceiptImageRepository receiptImageRepository) {
                return args -> {

                        Chef juan = chefRepository
                                        .save(new Chef("juan", "Juan", "Perez", LocalDate.parse("1980-01-01"),
                                                        "/images/juan.png", "123", "CHEF"));
                        Chef maria = chefRepository
                                        .save(new Chef("maria", "María", "Gomez", LocalDate.parse("1990-07-15"),
                                                        "/images/maria.png", "123", "CHEF"));
                        Chef pedro = chefRepository
                                        .save(new Chef("pedro", "Pedro", "López", LocalDate.parse("1980-08-02"),
                                                        "/images/pedro.png", "123", "ADMIN"));
                        Chef ana = chefRepository.save(
                                        new Chef("ana", "Ana", "Martinez", LocalDate.parse("1985-05-20"),
                                                        "/images/ana.jpeg", "123", "ADMIN"));
                        Chef luis = chefRepository.save(
                                        new Chef("luis", "Luis", "Ramirez", LocalDate.parse("1975-03-10"),
                                                        "/images/luis.jpeg", "123", "ADMIN"));

                        ArrayList<Ingredient> ingredientsTortilla = new ArrayList<>();
                        Receipt tortilla = receiptRepository.save(new Receipt("Tortilla de patatas",
                                        "Receta tradicional española de patatas, huevos y cebolla.", juan,
                                        ingredientsTortilla, null));
                        ingredientsTortilla.add(new Ingredient("Patatas", "500 g", tortilla));
                        ingredientsTortilla.add(new Ingredient("Huevos", "4 u", tortilla));
                        ingredientsTortilla.add(new Ingredient("Cebolla", "1 u", tortilla));
                        ingredientRepository.saveAll(ingredientsTortilla);
                        receiptImageRepository.save(new ReceiptImage("images/tortilla1.jpeg", tortilla));
                        receiptImageRepository.save(new ReceiptImage("images/tortilla2.jpeg", tortilla));

                        ArrayList<Ingredient> ingredientsPaella = new ArrayList<>();
                        Receipt paella = receiptRepository.save(new Receipt("Paella valenciana",
                                        "Arroz con azafrán, verduras, carne y marisco.", maria, ingredientsPaella,
                                        null));
                        ingredientsPaella.add(new Ingredient("Arroz", "250 g", paella));
                        ingredientsPaella.add(new Ingredient("Azafrán", "0.05 g", paella));
                        ingredientsPaella.add(new Ingredient("Pollo", "200 g", paella));
                        ingredientsPaella.add(new Ingredient("Conejo", "200 g", paella));
                        ingredientRepository.saveAll(ingredientsPaella);
                        receiptImageRepository.save(new ReceiptImage("images/paella1.jpeg", paella));
                        receiptImageRepository.save(new ReceiptImage("images/paella2.jpeg", paella));

                        ArrayList<Ingredient> ingredientsSalmorejo = new ArrayList<>();
                        Receipt salmorejo = receiptRepository.save(new Receipt("Salmorejo cordobés",
                                        "Crema fría de tomate, pan, ajo y aceite de oliva.", pedro,
                                        ingredientsSalmorejo, null));
                        ingredientsSalmorejo.add(new Ingredient("Pan", "250 g", salmorejo));
                        ingredientsSalmorejo.add(new Ingredient("Ajo", "2 u", salmorejo));
                        ingredientsSalmorejo.add(new Ingredient("Aceite de oliva", "100 g", salmorejo));
                        ingredientsSalmorejo.add(new Ingredient("Tomate", "500 g", salmorejo));
                        ingredientRepository.saveAll(ingredientsSalmorejo);
                        receiptImageRepository.save(new ReceiptImage("images/salmorejo1.jpeg", salmorejo));
                        receiptImageRepository.save(new ReceiptImage("images/salmorejo2.jpeg", salmorejo));

                        // Gazpacho andaluz
                        ArrayList<Ingredient> ingredientsGazpacho = new ArrayList<>();
                        Receipt gazpacho = receiptRepository.save(new Receipt("Gazpacho andaluz",
                                        "Sopa fría de tomate, pepino, pimiento, cebolla y ajo.", ana,
                                        ingredientsGazpacho, null));
                        ingredientsGazpacho.add(new Ingredient("Tomate", "1 kg", gazpacho));
                        ingredientsGazpacho.add(new Ingredient("Pepino", "1 u", gazpacho));
                        ingredientsGazpacho.add(new Ingredient("Pimiento verde", "1 u", gazpacho));
                        ingredientsGazpacho.add(new Ingredient("Cebolla", "1 u", gazpacho));
                        ingredientsGazpacho.add(new Ingredient("Ajo", "1 diente", gazpacho));
                        ingredientsGazpacho.add(new Ingredient("Aceite de oliva", "100 ml", gazpacho));
                        ingredientRepository.saveAll(ingredientsGazpacho);
                        receiptImageRepository.save(new ReceiptImage("images/gazpacho1.jpeg", gazpacho));
                        receiptImageRepository.save(new ReceiptImage("images/gazpacho2.jpeg", gazpacho));

                        // Cocido madrileño
                        ArrayList<Ingredient> ingredientsCocido = new ArrayList<>();
                        Receipt cocido = receiptRepository.save(new Receipt("Cocido madrileño",
                                        "Guiso tradicional de garbanzos, carnes y verduras.", luis, ingredientsCocido,
                                        null));
                        ingredientsCocido.add(new Ingredient("Garbanzos", "300 g", cocido));
                        ingredientsCocido.add(new Ingredient("Morcillo de ternera", "200 g", cocido));
                        ingredientsCocido.add(new Ingredient("Gallina", "150 g", cocido));
                        ingredientsCocido.add(new Ingredient("Chorizo", "100 g", cocido));
                        ingredientsCocido.add(new Ingredient("Zanahoria", "2 u", cocido));
                        ingredientRepository.saveAll(ingredientsCocido);
                        receiptImageRepository.save(new ReceiptImage("images/cocido1.jpeg", cocido));
                        receiptImageRepository.save(new ReceiptImage("images/cocido2.jpeg", cocido));

                        System.out.println("Init database");
                };
        }
}
