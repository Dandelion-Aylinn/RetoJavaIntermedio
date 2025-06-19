/*
 Objetivo:
 Simular un sistema reactivo que monitorea signos vitales de pacientes críticos en tiempo real en una 
 Unidad de Cuidados Intensivos (UCI), aplicando backpressure para controlar el flujo de datos y encadenar 
 operaciones reactivas que filtren eventos anómalos.

 Contexto del reto:
 En una UCI moderna, los sensores de pacientes generan constantemente datos como:

    - Frecuencia cardíaca (FC)
    - Presión arterial (PA)
    - Nivel de oxígeno (SpO2)
 
 Estos datos se generan rápidamente, pero el sistema médico no puede procesar todo al mismo ritmo. 
 Debes construir un flujo reactivo que:

    - Filtre valores críticos (ej. FC muy alta/baja, PA fuera de rango, SpO2 bajo).
    - Aplique backpressure para controlar la velocidad de procesamiento, evitando saturar al personal médico.
    - Muestre alertas en consola sólo para eventos críticos.

 Instrucciones:
 1. Simula generación continua de datos usando Flux.interval() para 3 pacientes.

    - Cada paciente debe emitir eventos cada 300 ms con valores aleatorios para FC, PA y SpO2.
 
 2. Filtra los eventos críticos con estas condiciones:

                       Parámetro                	     Rango crítico
                       Frecuencia cardíaca	            < 50 o > 120 bpm
                       Presión arterial	          < 90/60 mmHg o > 140/90 mmHg
                       Oxígeno en sangre	                 < 90%
 
 3. Implementa backpressure con delayElements() para procesar eventos críticos a un ritmo más lento (1 seg).
 4. Muestra alertas en consola, por ejemplo:

                                Paciente 2 - FC crítica: 130 bpm
                                Paciente 1 - SpO2 baja: 85%

 */

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.time.Duration;
import java.util.Random;

public class MonitorSignosVitales {

    static class SignosVitales {
        int pacienteId;
        int frecuenciaCardiaca; 
        int presionSistolica;   
        int presionDiastolica;  
        int spo2;               

        SignosVitales(int pacienteId, int fc, int ps, int pd, int spo2) {
            this.pacienteId = pacienteId;
            this.frecuenciaCardiaca = fc;
            this.presionSistolica = ps;
            this.presionDiastolica = pd;
            this.spo2 = spo2;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

       
        for (int i = 1; i <= 3; i++) {
            int pacienteId = i;

            Flux.interval(Duration.ofMillis(300))
                .map(tick -> new SignosVitales(
                        pacienteId,
                        40 + random.nextInt(100),  
                        80 + random.nextInt(80),   
                        50 + random.nextInt(40),   
                        85 + random.nextInt(15)    
                ))
                .filter(sv ->
                        sv.frecuenciaCardiaca < 50 || sv.frecuenciaCardiaca > 120 ||
                        sv.presionSistolica < 90 || sv.presionSistolica > 140 ||
                        sv.presionDiastolica < 60 || sv.presionDiastolica > 90 ||
                        sv.spo2 < 90
                )
                .delayElements(Duration.ofSeconds(1)) 
                .publishOn(Schedulers.parallel())
                .subscribe(sv -> {
                    if (sv.frecuenciaCardiaca < 50 || sv.frecuenciaCardiaca > 120) {
                        System.out.printf("Paciente %d - FC crítica: %d bpm%n", sv.pacienteId, sv.frecuenciaCardiaca);
                    }
                    if (sv.presionSistolica < 90 || sv.presionSistolica > 140 ||
                        sv.presionDiastolica < 60 || sv.presionDiastolica > 90) {
                        System.out.printf("Paciente %d - PA crítica: %d/%d mmHg%n", sv.pacienteId, sv.presionSistolica, sv.presionDiastolica);
                    }
                    if (sv.spo2 < 90) {
                        System.out.printf("Paciente %d - SpO2 baja: %d%%%n", sv.pacienteId, sv.spo2);
                    }
                });
        }

        
        Thread.sleep(30000); 
    }
}