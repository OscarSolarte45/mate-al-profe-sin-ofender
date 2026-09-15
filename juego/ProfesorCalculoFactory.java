public class ProfesorCalculoFactory extends ProfesorFactory {

    @Override
    public Profesor crearProfesor() {

        return new Profesor.ProfesorBuilder()
                .setNombre("Profe de Cálculo")
                .setMateria("Matemáticas")
                .setVidaMaxima(150)
                .build();
    }
}