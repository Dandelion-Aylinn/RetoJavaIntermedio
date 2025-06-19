/*
 Objetivo:
 Aplicar composición funcional en Java utilizando Stream API y flatMap para procesar listas 
 anidadas (encuestas de distintas sucursales de una clínica), filtrar respuestas específicas y 
 transformar los datos en mensajes útiles para el área de calidad.

 Contexto del reto:
Una clínica recopila encuestas de satisfacción de pacientes en distintas sucursales.
Cada encuesta incluye:
    - El nombre del paciente.
    - El comentario (puede ser null si no dejó uno).
    - La calificación (escala del 1 al 5).
El área de calidad desea:
    - Filtrar solo las encuestas con calificación menor o igual a 3 (pacientes insatisfechos).
    - Recuperar los comentarios disponibles (sin null) de esas encuestas.
    - Transformar cada comentario en un mensaje de seguimiento para la sucursal correspondiente.

Instrucciones:
 1. Crear las clases Encuesta y Sucursal
  - Encuesta:
     - paciente (String)
     - comentario (String, puede ser null)
     - calificacion (int)
    Implementa un método getComentario() que devuelva un Optional<String>.
  - Sucursal:
     - nombre (String)
     - Lista de encuestas (List<Encuesta>)
 2. Procesar las encuestas con Stream API y flatMap
    1. Desanidar todas las encuestas de las sucursales usando flatMap.
    2. Filtrar solo las encuestas con calificación menor o igual a 3.
    3. Recuperar los comentarios disponibles usando Optional.
    4. Transformar cada comentario en un mensaje:
        Sucursal [Nombre]: Seguimiento a paciente con comentario: "<comentario>"
    5. Mostrar todos los mensajes en consola.
 */
import java.util.Optional;

public class Encuesta{
    private String paciente;
    private String comentario;
    private int calificacion;

    public Encuesta(String paciente, String comentario, int calificacion){
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getCalificacion(){
        return calificacion;
    }

    public Optional<String> getComentario(){
        return Optional.ofNullable(comentario);
    }

    public String getPaciente(){
        return paciente;
    }
}