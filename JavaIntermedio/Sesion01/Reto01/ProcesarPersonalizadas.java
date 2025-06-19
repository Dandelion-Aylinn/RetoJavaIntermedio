import java.util.List;
public class ProcesarPersonalizadas{ 
public static void ProcesarPersonalizadas(List <? super OrdenPersonalizada> lista, int costoAdicional){
    for (Object obj: lista){
        if (obj instanceof OrdenPersonalizada){
            OrdenPersonalizada orden = (OrdenPersonalizada) obj;
            orden.aplicarCostoAdicional(costoAdicional);
        }
    }
}
}