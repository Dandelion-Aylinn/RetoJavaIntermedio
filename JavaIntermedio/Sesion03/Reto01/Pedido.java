/*
 Objetivo:
 Aplicar Optional y Stream API para filtrar y transformar una lista de pedidos de una pizzería,
 asegurando el manejo seguro de datos incompletos (como números telefónicos) y generando mensajes 
 personalizados para confirmar los envíos.

 Contexto del reto:
 En una pizzería, algunos clientes han realizado pedidos para entrega a domicilio, 
 pero no todos han dejado su número de teléfono para la confirmación.
 El sistema debe:
     - Filtrar solo los pedidos que sean para entrega a domicilio.
     - Recuperar y utilizar solo los números de teléfono disponibles (sin null).
     - Transformar cada número en un mensaje de confirmación.

Instrucciones:
 1. Crear la clase Pedido
    Atributos:
     - cliente (String)
     - tipoEntrega (String) → "domicilio" o "local"
     - telefono (String, puede ser null)
    Implementar el método getTelefono() que devuelva un Optional<String>.
 2. Procesar la lista de pedidos usando Stream API
        1. Filtrar solo los pedidos con tipo de entrega "domicilio".
        2. Recuperar los teléfonos disponibles usando Optional.
        3. Transformar cada teléfono en un mensaje de confirmación como:
            📞 Confirmación enviada al número: 555-1234
        4. Mostrar todos los mensajes en consola.

 */

import java.util.Optional;

public class Pedido{
    private String cliente;
    private String tipoEntrega;
    private String telefono;

    public Pedido (String cliente, String tipoEntrega, String telefono){
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getTipoEntrega(){
        return tipoEntrega;
    }

    public Optional<String> getTelefono(){
        return Optional.ofNullable(telefono);
    }

    public String getCliente(){
        return cliente;
    }
}