public class ProfesorCalculoFactory extends ProfesorFactory {

    @Override
    public Profesor crearProfesor() {

        return new Profesor.Builder()
                .setNombre("Profe de Cálculo")
                .setMateria("Matemáticas")
                .setVidaMaxima(150)
                .build();
    }
}