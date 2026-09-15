public enum ZonaCuerpo {
    CABEZA("Cabeza", 2.0, 0.50),      
    TORSO("Torso", 1.0, 0.90),         
    EXTREMIDADES("Extremidades", 0.6, 0.95);

    private final String nombre;
    private final double multiplicadorGolpe;
    private final double probabilidadAcierto;

    ZonaCuerpo(String nombre, double multiplicadorGolpe, double probabilidadAcierto) {
        this.nombre = nombre;
        this.multiplicadorGolpe = multiplicadorGolpe;
        this.probabilidadAcierto = probabilidadAcierto;
    }
    
    public String getNombre() {
        return nombre;
    }

    public double getMultiplicadorGolpe() {
        return multiplicadorGolpe;
    }

    public double getProbabilidadAcierto() {
        return probabilidadAcierto;
    }
}
