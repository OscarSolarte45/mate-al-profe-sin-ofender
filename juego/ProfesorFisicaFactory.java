public class ProfesorFisicaFactory extends ProfesorFactory {

    @Override
    public Profesor crearProfesor() {

        return new Profesor.ProfesorBuilder()
                .setNombre("Profe de Física")
                .setMateria("Ciencias")
                .setVidaMaxima(200)
                .build();
    }
}