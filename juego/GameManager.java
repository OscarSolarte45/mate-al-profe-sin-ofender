public class GameManager {
    private static GameManager instancia;
    private Profesor profesorActual;
    private Arma armaActual;

    private GameManager() {
    }

    public static GameManager getInstancia() {
        if (instancia == null) {
            instancia = new GameManager();
        }
        return instancia;
    }

    public void iniciarPartida(Profesor profesor) {
        this.profesorActual = profesor;
    }

    public Profesor getProfesorActual() {
        return profesorActual;
    }

    public void seleccionarArma(Arma arma) {
        this.armaActual = arma;
    }

    public Arma getArmaActual() {
        return armaActual;
    }
}