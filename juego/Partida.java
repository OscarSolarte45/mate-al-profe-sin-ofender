public class Partida {
    private Profesor profesor;

    public Partida(Profesor profesor) {
        this.profesor = profesor;
    }

    public void atacar(Arma arma, ZonaCuerpo zona) {
        double probabilidadExito = arma.getPrecision() * zona.getProbabilidadAcierto();
        double tirada = Math.random();

        if (tirada <= probabilidadExito) {
            int golpeFinal = (int) (arma.getGolpebase() * zona.getMultiplicadorGolpe());
            profesor.recibirGolpe(golpeFinal);

            System.out.println("\nLE PEGASTE! Le diste en "+ zona.getNombre());
            System.out.println("Daño causado: "+ golpeFinal);
            System.out.println("Vida restante: "+ profesor.getVidaActual()+ "/"+ profesor.getVidaMaxima());
        } else {
            System.out.println("\nLE FALLASTE!!");
            System.out.println("Le apuntaste a "+ zona.getNombre()+ " pero se te desvio un poco.");
        }
    }
    public Profesor getProfesor() {
        return profesor;
    }
}