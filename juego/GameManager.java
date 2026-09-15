public class GameManager {

    private static GameManager instancia;

    private Profesor profesorActual;
    private Arma armaActual;
    private Partida partidaActual;

    private GameManager() {
    }

    public static GameManager getInstancia() {

        if (instancia == null) {
            instancia = new GameManager();
        }

        return instancia;
    }

    public Partida iniciarPartida(Profesor profesor) {
        this.profesorActual = profesor;
        this.partidaActual = new Partida(profesor);
        return this.partidaActual;
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