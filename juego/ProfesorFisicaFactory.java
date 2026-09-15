public class ProfesorFisicaFactory extends ProfesorFactory {

    @Override
    public Profesor crearProfesor() {

        return new Profesor.Builder()
                .setNombre("Profe de Física")
                .setMateria("Ciencias")
                .setVidaMaxima(200)
                .build();
    }
}