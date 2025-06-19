/*
 Objetivo:
 Reforzar la creación de entidades con JPA, usando el mismo proyecto del ejemplo anterior para agregar 
 validaciones y consultas específicas en la clase Producto, simulando un sistema de inventario más realista.

 Instrucciones:
 Importante:
 No es necesario crear un nuevo proyecto. Este reto debe resolverse utilizando el mismo proyecto creado 
 durante el Ejemplo 01 (inventario), extendiendo la lógica de la clase Producto y del ProductoRepository.

 1. Asegúrate de tener tu clase Producto creada con los siguientes atributos:

    - id (tipo Long, llave primaria generada automáticamente)
    - nombre (tipo String)
    - descripcion (tipo String)
    - precio (tipo double)
 
 2. Implementa las siguientes validaciones en la entidad:

    - @NotBlank en nombre y descripcion
    - @Min(1) en precio
    - Puedes usar jakarta.validation.constraints y asegurarte de tener la dependencia 
      spring-boot-starter-validation.

 3. En ProductoRepository, agrega los siguientes métodos personalizados:

                List<Producto> findByPrecioGreaterThan(double precio);
                List<Producto> findByNombreContainingIgnoreCase(String nombre);
                List<Producto> findByPrecioBetween(double min, double max);
                List<Producto> findByNombreStartingWithIgnoreCase(String prefijo);

 4. Prueba estas consultas y validaciones desde CommandLineRunner:

    - Guarda al menos 4 productos
    - Imprime todos los productos con precio mayor a 500
    - Imprime todos los productos que contengan "lap" en su nombre
    - Imprime productos con precio entre 400 y 1000
    - Imprime productos cuyo nombre comience con "m" o "M"
 
 5. Muestra la salida en consola con System.out.println()
    Al ejecutar el programa verás una salida similar a:

    📦 Productos con precio mayor a 500:
        Producto[id=1, nombre='Laptop Lenovo', precio=12500.00]
        Producto[id=3, nombre='Teclado Mecánico', precio=950.00]
        Producto[id=4, nombre='Monitor', precio=3200.00]

    🔍 Productos que contienen 'lap':
        Producto[id=1, nombre='Laptop Lenovo', precio=12500.00]

    🎯 Productos con precio entre 400 y 1000:
        Producto[id=3, nombre='Teclado Mecánico', precio=950.00]

    📘 Productos cuyo nombre empieza con 'm':
        Producto[id=2, nombre='Mouse Logitech', precio=350.00]
        Producto[id=4, nombre='Monitor', precio=3200.00]

 */