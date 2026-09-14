public class ProfesorCalculoFactory extends ProfesorFactory {

    @Override
    public Profesor crearProfesor() {
        return new Profesor(
                "Profe de Cálculo",
                "Matemáticas",
                150
        );
    }
}