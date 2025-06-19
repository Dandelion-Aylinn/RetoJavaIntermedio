import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main{
    public static void main (String []args){
        Sucursal sucursalNorte = new Sucursal("Norte", Arrays.asList(
            new Encuesta ("Abril", "Todo ok", 4),
            new Encuesta ("María", null, 2),
            new Encuesta ("Abel", "Pesimo servicio", 1),
            new Encuesta ("Miguel", "Buena atención al cliente", 5),
            new Encuesta ("Aylinn", "Todo fue bueno y la queso!", 4),
            new Encuesta ("Sonia", null,3)
        ));

        List<Sucursal> sucursales = Arrays.asList(sucursalNorte);

        sucursales.stream()
            .flatMap(sucursal ->
                sucursal.getEncuestas().stream()
                    .filter(e -> e.getCalificacion() <= 3)
                    .flatMap(e ->
                        e.getComentario()
                            .map(comentario -> Stream.of("Sucursal " + sucursal.getNombre() +
                                ": Seguimiento a paciente con comentario: \"" + comentario + "\""))
                            .orElseGet(Stream::empty)
                    )
            )
            .forEach(System.out::println);
    }
}
   