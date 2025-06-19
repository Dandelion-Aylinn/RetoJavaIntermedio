public class Articulo extends MaterialCurso{
    private int palabras;

    public Articulo (String titulo, String autor, int palabras){
        super(titulo,autor);
        this.palabras = palabras;
    }

    @Override
    public void mostrarDetalle(){
        System.out.println("El articulo  " + titulo + " del autor " + autor + " tiene un total de " + palabras + "palabras");
    }
}