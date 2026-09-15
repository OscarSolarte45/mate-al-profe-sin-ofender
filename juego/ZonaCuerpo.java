public enum ZonaCuerpo {
    // Definimos las 3 zonas con: (Nombre, Multiplicador de Daño, Probabilidad de Acierto de la Zona)
    CABEZA("Cabeza", 2.0, 0.50),        // Hace el doble de daño, pero 50% de probabilidad de acierto
    TORSO("Torso", 1.0, 0.90),          // Daño estándar, 90% de acierto
    EXTREMIDADES("Extremidades", 0.6, 0.95); // Daño bajo, pero casi seguro de acertar (95%)

    // Atributos de cada zona
    private final String nombre;
    private final double multiplicadorGolpe;
    private final double probabilidadAcierto;

    // Constructor privado del Enum
    ZonaCuerpo(String nombre, double multiplicadorGolpe, double probabilidadAcierto) {
        this.nombre = nombre;
        this.multiplicadorGolpe = multiplicadorGolpe;
        this.probabilidadAcierto = probabilidadAcierto;
    }

    // Getters para consultar los valores desde el juego
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
