/*
Objetivo:
Simular el comportamiento paralelo de varios subsistemas críticos durante una misión espacial 
utilizando programación concurrente con Runnable, Callable, ExecutorService y Future.

Contexto del reto:
Durante una misión aeroespacial, múltiples sistemas trabajan simultáneamente para garantizar el éxito 
y la seguridad de la operación. En este reto, representarás 4 subsistemas:
     - Sistema de navegación – Calcula trayectoria y correcciones orbitales.
     - Sistema de soporte vital – Monitorea presión, oxígeno y condiciones internas.
     - Sistema de control térmico – Supervisa temperaturas internas y externas.
     - Sistema de comunicaciones – Establece contacto con la estación terrestre.
Cada uno se ejecutará como una tarea independiente en un hilo concurrente.

Instrucciones:

    Paso 1: Crear clases que simulen subsistemas
        - Crea una clase por cada sistema que implemente Callable<String>.
        - Simula el procesamiento con Thread.sleep() y devuelve un mensaje representando el estado del sistema.

public class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000);
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
}

    Paso 2: Ejecutar tareas con ExecutorService
        - Usa Executors.newFixedThreadPool(4)
        - Envía las tareas con submit()
        - Recupera los resultados con Future.get()

ExecutorService executor = Executors.newFixedThreadPool(4);
Future<String> nav = executor.submit(new SistemaNavegacion());
// ...otros sistemas
System.out.println(nav.get());

    Paso 3: Mostrar los resultados al finalizar
    Asegúrate de imprimir todos los resultados y cerrar el executor con shutdown().
 */

 import java.util.concurrent.Callable;
 public class SistemaNavegacion implements Callable<String>{
    @Override
    public String call() throws Exception{
        Thread.sleep(1000);
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
 }