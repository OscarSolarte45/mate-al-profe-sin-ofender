public class ProfesorFisicaFactory extends ProfesorFactory {

    @Override
    public Profesor crearProfesor() {
        return new Profesor(
                "Profe de Física",
                "Ciencias",
                200
        );
    }
}
