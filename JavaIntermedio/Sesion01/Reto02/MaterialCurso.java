/*
 Objetivo:
 Utilizar genéricos, wildcards (?, extends, super) y restricciones de tipo para gestionar 
 diferentes materiales de un curso (videos, artículos, ejercicios) en una plataforma educativa, 
 aplicando filtros y acciones específicas por tipo de material.

 Contexto del reto:
 En una plataforma educativa online, los cursos están compuestos por diferentes tipos de materiales:
    - Videos
    - Artículos
    - Ejercicios
 El sistema debe:
  1. Mostrar todos los materiales disponibles de un curso.
  2. Filtrar solo los videos para contar su duración total.
  3. Actualizar los materiales de tipo ejercicio, marcándolos como revisados.

 Instrucciones:
  1. Define una clase abstracta MaterialCurso con:
    - titulo (String)
    - autor (String)
    - Método abstracto mostrarDetalle().
  2. Crea las subclases:
    - Video (agrega duracion en minutos).
    - Articulo (agrega palabras como conteo).
    - Ejercicio (agrega revisado como booleano).
  3. Implementa los siguientes métodos genéricos:
    - mostrarMateriales(List<? extends MaterialCurso> lista)
      (Muestra el detalle de todos los materiales).
    - contarDuracionVideos(List<? extends Video> lista)
      (Suma y muestra la duración total de los videos).
    - marcarEjerciciosRevisados(List<? super Ejercicio> lista)
      (Actualiza el estado de los ejercicios a revisado = true y muestra un mensaje por cada uno).
  4. En el main, crea una lista con al menos 2 videos, 2 artículos y 2 ejercicios, 
     y prueba los métodos anteriores.

Desafio adicional:
Implementa un método genérico que filtre materiales por autor usando Predicate<MaterialCurso>.
 */

 public abstract class MaterialCurso{
    protected String titulo;
    protected  String autor;

    public MaterialCurso(String titulo,String autor){
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public abstract void mostrarDetalle();

 }