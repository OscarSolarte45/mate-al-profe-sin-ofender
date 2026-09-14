public class ProfesorProgramacionFactory extends ProfesorFactory {

    @Override
    public Profesor crearProfesor() {
        return new Profesor(
                "Profe de Programación",
                "Sistemas",
                100
        );
    }
}