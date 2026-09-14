import java.util.Random;

public class AtaqueEstandar implements EstrategiaAtaque {
    private Random random = new Random();

    @Override
    public boolean ejecutarAtaque(Arma arma, ZonaCuerpo zona, Profesor profesor, ObservadorJuego observador) {
        double probabilidadExito = arma.getPrecision() * zona.getProbabilidadAcierto();
        double tirada = random.nextDouble();

        if (tirada <= probabilidadExito) {
            int danioFinal = (int) (arma.getGolpebase() * zona.getMultiplicadorGolpe());
            profesor.recibirGolpe(danioFinal);
            observador.alNotificar("¡IMPACTO DIRECTO en la " + zona.getNombre() + "! Se infligieron " + danioFinal + " puntos de daño.");
            return true;
        } else {
            observador.alNotificar("¡HAS FALLADO! El ataque hacia la " + zona.getNombre() + " no logró impactar.");
            return false;
        }
    }
}