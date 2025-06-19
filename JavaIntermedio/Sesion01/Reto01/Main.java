import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main (String [] args){
        OrdenMasa orden1 = new OrdenMasa ("P001", 2);
        OrdenMasa orden2 = new OrdenMasa ("P002", 5);

        OrdenPersonalizada orden3 = new OrdenPersonalizada("P003", 10, "Nancy");
        OrdenPersonalizada orden4 = new OrdenPersonalizada("P004", 4, "Ana");

        OrdenPrototipo orden5 = new OrdenPrototipo ("P005", 1, "Fase 1");
        OrdenPrototipo orden6 = new OrdenPrototipo ("P006", 1, "Fase 2");

        List<OrdenProduction> todasLasOrdenes = new ArrayList<>();
        todasLasOrdenes.add(orden1);
        todasLasOrdenes.add(orden2);
        todasLasOrdenes.add(orden3);
        todasLasOrdenes.add(orden4);
        todasLasOrdenes.add(orden5);
        todasLasOrdenes.add(orden6);

        System.out.println("Las ordenes son:");
        mostrarOrdenes(todasLasOrdenes);

        List<OrdenProduction> listaMixta = new ArrayList<>(todasLasOrdenes);
        System.out.println("\n Procesando órdenes personalizadas con costo adicional:");
        procesarPersonalizadas(listaMixta, 300);
    }

    public static void mostrarOrdenes(List<? extends OrdenProduction> lista) {
        for (OrdenProduction orden : lista) {
            orden.mostrarResumen();
            System.out.println("---");
        }
    }

    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                OrdenPersonalizada orden = (OrdenPersonalizada) obj;
                orden.aplicarCostoAdicional(costoAdicional);
            }
        }
    }
}
    

