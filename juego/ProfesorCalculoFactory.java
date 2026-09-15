public class ProfesorCalculoFactory extends ProfesorFactory {
    @Override
    public Profesor crearProfesor() {
        return new Profesor.ProfesorBuilder()
                .setNombre("Profe de Calculo")
                .setMateria("Calculo Diferencial")
                .setVidaMaxima(150)
                .build();
    }
}