package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductoRepository productoRepo, MarcaRepository marcaRepo) {
        return (args) -> {
            // Crear marcas
            Marca apple = new Marca("Apple");
            Marca samsung = new Marca("Samsung");

            marcaRepo.save(apple);
            marcaRepo.save(samsung);

            // Crear productos para cada marca
            productoRepo.save(new Producto("iPhone 15", "Smartphone de última generación", 23000.00, apple));
            productoRepo.save(new Producto("iPad Pro", "Tablet profesional", 18000.00, apple));
            productoRepo.save(new Producto("Galaxy S23", "Smartphone con cámara potente", 21000.00, samsung));
            productoRepo.save(new Producto("Smart TV", "Televisión inteligente 4K", 15000.00, samsung));

            // Mostrar productos agrupados por marca
            System.out.println("📚 Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println("🏷️ " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                    .filter(p -> p.getMarca().getId().equals(marca.getId()))
                    .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}