/*
 Objetivo: 
 Implementar genéricos y wildcards para gestionar diferentes tipos de órdenes de producción 
 en una planta industrial, clasificando entre producción en masa, personalizada y prototipos.
 Además, deberás procesar las órdenes utilizando métodos flexibles con restricciones de tipo.

 Contexto del reto:
 Imagina que trabajas en una planta industrial que produce:

    - Órdenes de producción en masa (productos estándar).
    - Órdenes personalizadas (adaptadas a cliente).
    - Prototipos (productos en prueba).
Debes implementar un sistema que:

 1. Gestione listas de órdenes de diferentes tipos (usando genéricos).
 2. Muestre información de las órdenes sin importar el tipo.
 3. Procese las órdenes personalizadas, agregando un costo adicional por ajuste.

 Instrucciones:

 1. Crea una clase abstracta llamada OrdenProduccion con los siguientes atributos:
    - codigo (String)
    - cantidad (int)
    - Incluye un método mostrarResumen() para imprimir información básica.
 2. Crea tres subclases:
    - OrdenMasa (producción en masa)
    - OrdenPersonalizada (agrega cliente como atributo)
    - OrdenPrototipo (agrega faseDesarrollo como atributo)
 3. Implementa un método genérico:
    - mostrarOrdenes(List<? extends OrdenProduccion> lista)
    - (Debe leer cualquier tipo de orden y mostrar sus datos).
 4. Implementa otro método:
    - procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional)
      (Debe modificar solo las órdenes personalizadas, mostrando un mensaje con el costo agregado).
 5. En el método main, crea listas con varios tipos de órdenes (mínimo 2 por tipo) 
    y prueba los métodos anteriores.

 */

public abstract class OrdenProduction{
    protected  String codigo;
    protected  int cantidad;

    public OrdenProduction(String codigo, int cantidad){
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public void mostrarResumen(){
        System.out.println("Código: " + codigo + ", cantidad: " + cantidad);
    }
}