import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{
    public static void main(String[] args){
        RecursoMedico salaCirugia = new RecursoMedico ("Sala de cirugía");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new ProfesionalMedico("Dra. Franco", salaCirugia));
        executor.submit(new ProfesionalMedico("Dr. Santiago", salaCirugia));
        executor.submit(new ProfesionalMedico("Dr. Julión", salaCirugia));
        executor.submit(new ProfesionalMedico("Dra. Julieta", salaCirugia));

        executor.shutdown();
    }
}