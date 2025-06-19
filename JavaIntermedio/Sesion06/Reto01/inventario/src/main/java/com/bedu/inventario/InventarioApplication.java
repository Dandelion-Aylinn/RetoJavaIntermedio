package com.bedu.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.validation.ConstraintViolationException;

@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

	@Bean
    public CommandLineRunner demo(ProductoRepository repository) {
        return (args) -> {

			try{
			repository.save(new Producto("Laptop", "Portátil de 16 pulgadas", 1200.00));
            repository.save(new Producto("Teclado mecánico", "Switch azul", 800.00));
            repository.save(new Producto("Mouse gamer", "Alta precisión", 600.00));
			} catch (ConstraintViolationException e) {
                e.getConstraintViolations().forEach(cv -> System.out.println(cv.getMessage()));
            }
            
            // Mostrar todos los productos
            System.out.println("📂 Productos disponibles:");
            repository.findAll().forEach(System.out::println);

            // Buscar por nombre parcial
            System.out.println("\n🔍 Productos que contienen 'Lap':");
            repository.findByNombreContaining("Lap").forEach(System.out::println);

			System.out.println("📦 Productos con precio mayor a 500:");
            repository.findByPrecioGreaterThan(500).forEach(System.out::println);

			System.out.println("\n🔍 Productos que contienen 'lap':");
            repository.findByNombreContainingIgnoreCase("lap").forEach(System.out::println);

			System.out.println("\n🎯 Productos con precio entre 400 y 1000:");
            repository.findByPrecioBetween(400, 1000).forEach(System.out::println);

			System.out.println("\n📘 Productos cuyo nombre empieza con 'm':");
            repository.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
        };
    }
}

