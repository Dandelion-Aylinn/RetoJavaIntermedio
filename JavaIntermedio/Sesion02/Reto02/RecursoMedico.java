/*
Objetivo:
Simular una situación hospitalaria donde múltiples profesionales médicos necesitan acceder a un 
recurso crítico (como una sala de cirugía), aplicando sincronización con ReentrantLock para evitar 
condiciones de carrera y garantizar la integridad del sistema.

Contexto del reto:
En hospitales, algunos recursos como quirófanos, equipos de resonancia magnética o camas de cuidados 
intensivos, solo pueden ser usados por un profesional a la vez. Este reto representa esa situación 
utilizando múltiples hilos que intentan acceder a un mismo recurso compartido.

Instrucciones:
 1. Crear una clase RecursoMedico
    - Debe tener un atributo String nombre que represente el recurso (ej. "Sala de cirugía").
    - Implementa un método usar(String profesional) que simule:
    - La entrada de un profesional al recurso
    - El tiempo de uso (puede usar Thread.sleep())
    - La salida del recurso
    - Usa un ReentrantLock para asegurar que solo un hilo acceda al recurso a la vez.
 2. Crear tareas que representen a profesionales médicos
    - Implementa varias clases o lambdas que usen Runnable.
    - Cada una representa a un médico o enfermero intentando usar el recurso médico.
    - El nombre del hilo debe indicar quién accede (ej. "Dra. Sánchez")
 3. Ejecutar la simulación
    - Usa un ExecutorService con al menos 4 hilos para simular concurrencia.
    - Ejecuta todas las tareas e imprime en consola el flujo de uso del recurso.
 */

import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico{
    private String nombre;
    private ReentrantLock lock = new ReentrantLock(); //Control del acceso

    public RecursoMedico(String nombre){
        this.nombre = nombre;
    }

    public void usar(String profesional){
        lock.lock();
        try{
            System.out.println(profesional + " ha ingresado a " + nombre);
            Thread.sleep(1800);
            System.out.println(profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally{
            lock.unlock();
        }
    }
}