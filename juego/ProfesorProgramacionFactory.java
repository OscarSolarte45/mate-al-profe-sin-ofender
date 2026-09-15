public class ProfesorProgramacionFactory extends ProfesorFactory {

    @Override
    public Profesor crearProfesor() {

        return new Profesor.ProfesorBuilder()
                .setNombre("Profe de Programación")
                .setMateria("Sistemas")
                .setVidaMaxima(100)
                .build();
    }
}