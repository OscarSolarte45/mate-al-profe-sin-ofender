public class Profesor {
    private String nombre;
    private String materia;
    private int vidaActual;
    private int vidaMaxima;

    public Profesor(String nombre, String materia, int vidaMaxima) {
        this.nombre = nombre;
        this.materia = materia;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
    }

    public void recibirGolpe(int cantidad) {
        this.vidaActual -= cantidad;
        if (this.vidaActual < 0) {
            this.vidaActual = 0;
        }
    }

    public boolean seMurio() {
        return this.vidaActual <= 0;
    }

    public String getNombre() {
        return nombre; 
    }
    public String getMateria() {
        return materia; 
    }
    public int getVidaActual() {
        return vidaActual; 
    }
    public int getVidaMaxima() { 
        return vidaMaxima; 
    }
}