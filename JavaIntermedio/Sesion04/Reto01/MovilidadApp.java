/*
 Objetivo:
  Aplicar CompletableFuture para simular procesos asincrónicos en una app de movilidad (tipo Uber o DiDi), 
  realizando tareas en paralelo como calcular la ruta y estimar la tarifa, 
  y enviando una notificación al usuario una vez finalizadas.

  Contexto del reto:
  En una app de movilidad, al solicitar un viaje:
    1. Se calcula la ruta óptima entre origen y destino.
    2. Se estima la tarifa considerando distancia y demanda.
    3. Una vez ambas operaciones terminan, se envía una confirmación al usuario con la información.
  Todo este flujo debe ser asincrónico y no bloqueante, permitiendo procesar otras solicitudes mientras 
  estas tareas se completan.

  Instrucciones:

    1. Crea una clase MovilidadApp con los siguientes métodos:

        - CompletableFuture<String> calcularRuta():
            - Simula calcular la ruta óptima (latencia de 2-3 segundos).
            - Retorna un mensaje como "Ruta: Centro -> Norte".

        - CompletableFuture<Double> estimarTarifa():
            - Simula la estimación de la tarifa (latencia de 1-2 segundos).
            - Retorna un valor numérico como 75.50.

        - Un método para combinar ambas operaciones:
            - Muestra un mensaje final como:
                🚗 Ruta calculada: Centro -> Norte | Tarifa estimada: $75.50

    2. Encadena las operaciones usando thenCombine y maneja errores con exceptionally 
       para casos donde alguna operación pueda fallar (simula con una excepción si quieres).
    
    3. Muestra los resultados en consola.
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MovilidadApp{
    public CompletableFuture<String> calcularRuta(){
        return CompletableFuture.supplyAsync(() ->{
            try {
                int delay = ThreadLocalRandom.current().nextInt(2,4);
                TimeUnit.SECONDS.sleep(delay);

                return "Centro -> Norte";

            } catch (InterruptedException e) {
                throw new RuntimeException("Error durante la espera en calcular Ruta");
            }
        });
    }

    public CompletableFuture<Double>estimarTarifa(){
        return CompletableFuture.supplyAsync(()->{
            try {
                int delay = ThreadLocalRandom.current().nextInt(1,3);
                TimeUnit.SECONDS.sleep(delay);
                return 75.50;
            } catch (InterruptedException e) {
            throw new RuntimeException("Error durante la espera de tarifa");
            }


        });
    }

    public void procesarSolicitud(){
        CompletableFuture<String> rutaFuture = calcularRuta()
        .exceptionally(ex ->{
            System.out.println("Ha fallado la ruta" + ex.getMessage());
            return "Ruta no disponible";
        });

        CompletableFuture<Double> tarifaFuture = estimarTarifa()
        .exceptionally(ex -> {
            System.out.println("Error en la tarifa " + ex.getMessage());
            return 0.0;
        });

        rutaFuture.thenCombine(tarifaFuture, (ruta,tarifa) ->{
            return " 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: " + tarifa;
        }).thenAccept(resultadoFinal -> {
            System.out.println(resultadoFinal);
        });
    }

    public static void main(String[] args) throws InterruptedException {
        MovilidadApp app = new MovilidadApp();
        app.procesarSolicitud();

        TimeUnit.SECONDS.sleep(5);
    }
}