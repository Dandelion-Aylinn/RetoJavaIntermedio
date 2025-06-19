import java.util.concurrent.*;

public class Main{
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> Navegacion = executor.submit(new SistemaNavegacion());
        Future<String> Vital = executor.submit(new SistemaSoporteVital());
        Future<String> Comunicaciones = executor.submit(new SistemaComunicaciones());
        Future<String> Termico = executor.submit(new SistemaControlTermico());

        try{
        System.out.println(Navegacion.get());
        System.out.println(Vital.get());
        System.out.println(Comunicaciones.get());
        System.out.println(Termico.get());
        } catch (InterruptedException | ExecutionException e){

            System.out.println(e.getMessage());

        } finally {

        executor.shutdown();
        System.out.println("✅ Todos los sistemas reportan estado operativo.");

        }

    }
}