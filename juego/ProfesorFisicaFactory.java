public class ProfesorFisicaFactory extends ProfesorFactory {
    @Override
    public Profesor crearProfesor() {
        return new Profesor.ProfesorBuilder()
                .setNombre("Profe de Fisica")
                .setMateria("Fisica Mecanica")
                .setVidaMaxima(200)
                .build();
    }
}
