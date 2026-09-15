public class Partida {

    private Profesor profesor;

    public Partida(Profesor profesor) {
        this.profesor = profesor;
    }

    public void atacar(Arma arma, ZonaCuerpo zona) {

        double probabilidadExito =
                arma.getPrecision()
                * zona.getProbabilidadAcierto();

        double tirada = Math.random();

        if (tirada <= probabilidadExito) {

            int danioFinal =
                    (int) (
                            arma.getGolpebase()
                            * zona.getMultiplicadorGolpe()
                    );

            profesor.recibirGolpe(danioFinal);

            System.out.println(
                    "\n¡IMPACTO DIRECTO en la "
                    + zona.getNombre()
                    + "!"
            );

            System.out.println(
                    "Daño causado: "
                    + danioFinal
            );

            System.out.println(
                    "Salud restante: "
                    + profesor.getVidaActual()
                    + "/"
                    + profesor.getVidaMaxima()
            );

        } else {

            System.out.println(
                    "\n¡HAS FALLADO!"
            );

            System.out.println(
                    "El ataque hacia la "
                    + zona.getNombre()
                    + " no logró impactar."
            );
        }
    }

    public Profesor getProfesor() {
        return profesor;
    }
}