public class OrdenPrototipo extends OrdenProduction{
    private String faseDesarrollo;

    public OrdenPrototipo(String codigo, int cantidad, String faseDesarrollo){
        super(codigo,cantidad);
        this.faseDesarrollo = faseDesarrollo;

    }

    @Override
    public void mostrarResumen(){
        super.mostrarResumen();
        System.out.println("Fase de desarrollo: " + faseDesarrollo);
    }
}
