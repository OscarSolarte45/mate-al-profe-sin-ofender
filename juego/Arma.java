public class Arma{
    private String nombre;
    private int golpebase;
    private double precision;

    public Arma(String nombre, int golpebase, double precision){
        this.nombre = nombre;
        this.golpebase = golpebase;
        this.precision = precision;
    }
    public String getNombre(){
        return nombre;
    }
    public int getGolpebase(){
        return golpebase;
    }
    public double getPrecision(){
        return precision;
    }
}