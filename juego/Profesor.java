public class Profesor {
    private String nombre;
    private String materia;
    private int vidaActual;
    private int vidaMaxima;

    private Profesor(ProfesorBuilder builder) {
        this.nombre = builder.nombre;
        this.materia = builder.materia;
        this.vidaMaxima = builder.vidaMaxima;
        this.vidaActual = builder.vidaMaxima;
    }

    public static class ProfesorBuilder {
        private String nombre;
        private String materia;
        private int vidaMaxima;

        public ProfesorBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ProfesorBuilder setMateria(String materia) {
            this.materia = materia;
            return this;
        }

        public ProfesorBuilder setVidaMaxima(int vidaMaxima) {
            this.vidaMaxima = vidaMaxima;
            return this;
        }

        public Profesor build() {
            return new Profesor(this);
        }
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