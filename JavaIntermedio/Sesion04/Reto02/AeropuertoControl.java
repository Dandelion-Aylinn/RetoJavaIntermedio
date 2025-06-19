/*
 Objetivo:
 Simular un flujo asincrónico y no bloqueante para la gestión de aterrizajes en un aeropuerto internacional, 
 integrando varias consultas en paralelo con CompletableFuture, combinando sus resultados y manejando errores 
 potenciales.

 Contexto del reto:
 En un aeropuerto internacional, al aproximarse un vuelo para aterrizaje, el sistema debe realizar consultas 
 asincrónicas para validar las condiciones del aterrizaje:

    - Disponibilidad de la pista (puede haber ocupación o mantenimiento).
    - Estado meteorológico (ej. tormentas, niebla, vientos fuertes).
    - Estado del tráfico aéreo cercano (otros vuelos aproximándose).
    - Disponibilidad de personal en tierra (personal de guía y seguridad).

El aterrizaje solo se autoriza si todas las condiciones son óptimas.
Este proceso debe ser no bloqueante y robusto, manejando errores si alguno de los servicios falla.

Instrucciones:
1. Crea una clase AeropuertoControl con los siguientes métodos:
    - CompletableFuture<Boolean> verificarPista()
    - CompletableFuture<Boolean> verificarClima()
    - CompletableFuture<Boolean> verificarTraficoAereo()
    - CompletableFuture<Boolean> verificarPersonalTierra()
   Cada método debe simular latencia (2-3 segundos) y devolver true si el servicio es favorable.

2. Ejecuta todas las verificaciones en paralelo usando CompletableFuture.

3. Combina los resultados usando thenCombine, thenCombineAsync o allOf para decidir si se 
   autoriza el aterrizaje.

4. Si alguna consulta falla o regresa false, muestra:
        🚫 Aterrizaje denegado: condiciones no óptimas.
   Si todo es exitoso:
        🛬 Aterrizaje autorizado: todas las condiciones óptimas.

5. Usa exceptionally para manejar cualquier error en el proceso.
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl{
    public CompletableFuture<Boolean>verificarPista(){
        return CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2,4));
                return true;    
            } catch (InterruptedException e) {
                throw new RuntimeException("Error en verificar pista");
            }
        });
    }

    public CompletableFuture<Boolean>verificarClima(){
        return CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2,4));
                return true;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar el clima");
            }
        });
    }
     public CompletableFuture<Boolean>verificarTrafico(){
        return CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2,4));
                return true;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar el trafico aereo");
            }
        });
     }
     public CompletableFuture<Boolean>verificarPersonalTerrestre(){
        return CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2,4));
                return true;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar personal en tierra");
            }
        });
     }

     public void autorizacionAterrizaje(){
        CompletableFuture<Boolean>pistaFuture = verificarPista()
        .exceptionally(ex -> {
            System.out.println("Error en pista: " + ex.getMessage());
            return false;
        });

        CompletableFuture<Boolean>climaFuture = verificarClima()
        .exceptionally(ex -> {
            System.out.println("Error en clima: " + ex.getMessage());
            return false;
        });

        CompletableFuture<Boolean>traficoFuture = verificarTrafico()
        .exceptionally(ex -> {
            System.out.println("Error en trafico aereo: " + ex.getMessage());
            return false;
        });

        CompletableFuture<Boolean>personalFuture = verificarPersonalTerrestre()
        .exceptionally(ex -> {
            System.out.println("Error con el personal: " + ex.getMessage());
            return false;
        });

        CompletableFuture.allOf(pistaFuture,climaFuture,traficoFuture,personalFuture)
        .thenRun(()->{
            try{
                boolean pista = pistaFuture.get();
                boolean clima = climaFuture.get();
                boolean trafico = traficoFuture.get();
                boolean personal = personalFuture.get();

                if(pista && clima && trafico && personal){
                    System.out.println("Aterrizaje autorizado: Condiciones optimas");
                }else{
                    System.out.println("Aterrizaje no autorizado: Condiciones no óptimas");
                }
            } catch(Exception e) {
                System.out.println("Error general " + e.getMessage());
            }
        });
     }

     public static void main(String[] args) throws InterruptedException {
         AeropuertoControl control = new AeropuertoControl();
         control.autorizacionAterrizaje();

         TimeUnit.SECONDS.sleep(6);
     }
}