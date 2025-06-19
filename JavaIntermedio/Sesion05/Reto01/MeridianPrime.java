/*
 Objetivo:
 Simular el flujo reactivo y no bloqueante para la gestión en tiempo real de los sistemas críticos de 
 Meridian Prime (una ciudad inteligente inspirada en Horizon Zero Dawn), usando Project Reactor y 
 Flux para procesar múltiples flujos concurrentes.

 Contexto del reto:
 Como chief engineer de Meridian Prime, debes gestionar cinco sistemas críticos en tiempo real:

    - 🚗 Sensores de tráfico → Detectan congestión en las principales avenidas.
    - 🌫️ Contaminación del aire → Generan alertas si superan los límites saludables.
    - 🚑 Accidentes viales → Prioriza emergencias según la gravedad del accidente.
    - 🚝 Trenes maglev → Controla la frecuencia y detecta retrasos.
    - 🚦 Semáforos inteligentes → Ajusta tiempos según la repetición de señales rojas.

 El sistema debe procesar estos flujos en paralelo, filtrar eventos críticos y responder de forma fluida 
 sin bloquear el sistema.

 Instrucciones:
 1. Crea cinco flujos Flux independientes, cada uno simulando un sistema crítico:
 Sistema	                                   Dato simulado	               Frecuencia sugerida
 Sensores de tráfico	                     Nivel de congestión (0-100%)	        Cada 500 ms
 Contaminación del aire	                 Nivel de partículas PM2.5 (ug/m3)      	Cada 600 ms
 Accidentes viales	                   Evento con prioridad (Baja, Media, Alta)	    Cada 800 ms
 Trenes maglev                            	Retraso en minutos (0-10 min)	        Cada 700 ms
 Semáforos inteligentes	                Estado (Verde, Amarillo, Rojo) por cruce	Cada 400 ms

 2. Filtra eventos críticos en cada flujo:

    - 🚗 Congestión mayor al 70%.
    - 🌫️ Contaminación superior a 50 ug/m3.
    - 🚑 Accidentes con prioridad Alta.
    - 🚝 Retrasos mayores a 5 minutos.
    -🚦 Semáforo en rojo más de 3 veces seguidas.

 3. Emite en consola los eventos críticos detectados con mensajes descriptivos.

 4. Simula backpressure en al menos un flujo (ej. sensores de tráfico o trenes maglev) 
    usando delayElements o onBackpressureBuffer.

 5. Implementa subscripciones separadas para cada flujo (puedes usar merge() si quieres combinarlos).
 */

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class MeridianPrime{
    private static final Random random = new Random ();

    public static void main(String[] args) throws InterruptedException{

        Flux<Double> trafficSensor = Flux.interval(Duration.ofMillis(500))
        .map(i -> 10 + (100 - 10) * random.nextDouble())
        .publishOn(Schedulers.parallel());

         Flux<Double> airPollution = Flux.interval(Duration.ofMillis(600))
                .map(i -> 10 + (100 - 10) * random.nextDouble()) 
                .publishOn(Schedulers.parallel());
        
        Flux<String> accidents = Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] priorities = {"Baja", "Media", "Alta"};
                    return priorities[random.nextInt(priorities.length)];
                })
                .publishOn(Schedulers.parallel());
        
        Flux<Integer> trainDelays = Flux.interval(Duration.ofMillis(700))
                .map(i -> random.nextInt(11)) 
                .publishOn(Schedulers.parallel());
        
        String[] states = {"Verde", "Amarillo", "Rojo"};
        AtomicInteger redCount = new AtomicInteger(0);

        Flux<String> trafficLights = Flux.interval(Duration.ofMillis(400))
                .map(i -> states[random.nextInt(states.length)])
                .publishOn(Schedulers.parallel());
        
        trafficSensor
                .filter(level -> level > 70)
                .subscribe(level ->
                System.out.printf("🚨 Congestión crítica: %.2f%%%n", level));

         airPollution
                .filter(pm25 -> pm25 > 50)
                .subscribe(pm25 ->
                        System.out.printf("💨 Contaminación crítica: %.2f ug/m3%n", pm25));
        
        accidents
                .filter(priority -> priority.equals("Alta"))
                .subscribe(priority ->
                        System.out.println("🚑 Emergencia vial PRIORIDAD ALTA"));
        
        trainDelays
                .filter(delay -> delay > 5)
                .onBackpressureBuffer() 
                .subscribe(delay ->
                        System.out.println("🚄 Retraso crítico: " + delay + " minutos"));
        
         trafficLights
                .map(state -> {
                    if (state.equals("Rojo")) {
                        if (redCount.incrementAndGet() > 3) {
                            return "🔴 Semáforo rojo repetido: " + redCount.get() + " veces";
                        }
                    } else {
                        redCount.set(0);
                    }
                    return null;
                })
                .filter(msg -> msg != null)
                .subscribe(System.out::println);

                 Thread.sleep(30000);
    }
}