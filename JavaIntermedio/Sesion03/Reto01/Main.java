import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main{
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("Abril", "Pick up", "5552221111"),
            new Pedido("Abel", "domicilio","5656565252"),
            new Pedido("Guadalupe", "domicilio", null),
            new Pedido("Miguel", "pick up", "5555555555"),
            new Pedido("Aylinn","domicilio",null),
            new Pedido("Sonia", "Punto medio", "5511334455")
        );

        pedidos.stream()

        .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))

        .flatMap(p -> p.getTelefono().map(Stream::of).orElseGet(Stream::empty))

        .map(telefono -> "📞 Confirmación enviada al número: " + telefono)

        .forEach(System.out::println);
    }
}