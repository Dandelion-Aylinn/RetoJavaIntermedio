public class OrdenPersonalizada extends OrdenProduction{
    private String cliente;

    public OrdenPersonalizada(String codigo, int cantidad, String cliente){
        super(codigo,cantidad);  
        this.cliente = cliente;      
    }

    @Override

    public void mostrarResumen(){
        super.mostrarResumen();
        System.out.println("Cliente: " + cliente);
    }

    public void aplicarCostoAdicional(int costo){
        System.out.println("Se aplica costo adicional: " + costo + " Cliente: " + cliente);
    }
}