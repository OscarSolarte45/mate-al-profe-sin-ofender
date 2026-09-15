public class ProfesorProgramacionFactory extends ProfesorFactory {
    @Override
    public Profesor crearProfesor() {
        return new Profesor.ProfesorBuilder()
                .setNombre("Profe de Programacion")
                .setMateria("POO")
                .setVidaMaxima(100)
                .build();
    }
}