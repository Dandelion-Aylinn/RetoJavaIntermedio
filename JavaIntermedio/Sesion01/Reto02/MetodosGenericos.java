import java.util.*;
import java.util.function.Predicate;

public class MetodosGenericos{
    public static void mostrarMateriales (List <? extends MaterialCurso> lista){
        for(MaterialCurso material : lista){
            material.mostrarDetalle();
        }
    }

    public static void contarDuracionVideos (List<? extends Video> lista) {
        int total = 0;
        for (Video v : lista){
            total += v.getDuracion();
        }

        System.out.println("La duración total de todos los videos es " + total + "minutos");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista){
        for (Object obj : lista){
            if (obj instanceof Ejercicio) {
                Ejercicio e = (Ejercicio) obj;
                e.marcarRevisado();
                System.out.println("Ejercicio " + e.titulo + " ha sido revisado ");
            }
        }
    }

    //Reto adicional

    public static List<MaterialCurso> filtrarPorAutor(List<MaterialCurso> lista, Predicate <MaterialCurso> filtro){
         List<MaterialCurso> resultado = new ArrayList<>();
    for (MaterialCurso material : lista) {
        if (filtro.test(material)) {
            resultado.add(material);
            }
        }
    return resultado;
    }

    public static void main(String[] args) {
        List <MaterialCurso> materiales = new ArrayList<>();

        //vides
        materiales.add (new Video("Introducción a Java","Mario", 15));
        materiales.add (new Video("POO en Java","Carlos", 20));

        //Articulo
        materiales.add (new Articulo("Historia de Java","Ana", 1200));
        materiales.add (new Articulo("Tipos de datos","Luis", 800));

        //Ejercicio
        materiales.add (new Ejercicio("Variables y tipos ","Luis"));
        materiales.add (new Ejercicio("Condicionales","Luis"));

        mostrarMateriales(materiales);

        List<Video> videos = new ArrayList<>();
        for (MaterialCurso m : materiales) {
            if (m instanceof Video) {
                videos.add((Video) m);
            }
        }
        System.out.println("Total de videos");
        contarDuracionVideos(videos);

        System.out.println("Ejercicios revisados");
        marcarEjerciciosRevisados(materiales);

        System.out.println("Después de marcados");
        mostrarMateriales(materiales);

        //Filtro por autor
        System.out.println("Filtro por autor: Carlos");
        List<MaterialCurso> deCarlos = filtrarPorAutor(materiales, m -> m.getAutor().equals("Carlos"));
        mostrarMateriales(deCarlos);

    }
}
