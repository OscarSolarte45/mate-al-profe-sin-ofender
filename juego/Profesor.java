public class Profesor {

    private String nombre;
    private String materia;
    private int vidaActual;
    private int vidaMaxima;

    private Profesor(Builder builder) {
        this.nombre = builder.nombre;
        this.materia = builder.materia;
        this.vidaMaxima = builder.vidaMaxima;
        this.vidaActual = builder.vidaMaxima;
    }

    // ==============================
    // BUILDER
    // ==============================

    public static class Builder {

        private String nombre;
        private String materia;
        private int vidaMaxima;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setMateria(String materia) {
            this.materia = materia;
            return this;
        }

        public Builder setVidaMaxima(int vidaMaxima) {
            this.vidaMaxima = vidaMaxima;
            return this;
        }

        public Profesor build() {
            return new Profesor(this);
        }
    }

    // ==============================
    // COMPORTAMIENTO DEL PROFESOR
    // ==============================

    public void recibirGolpe(int cantidad) {

        this.vidaActual -= cantidad;

        if (this.vidaActual < 0) {
            this.vidaActual = 0;
        }
    }

    public boolean seMurio() {
        return this.vidaActual <= 0;
    }

    // ==============================
    // GETTERS
    // ==============================

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