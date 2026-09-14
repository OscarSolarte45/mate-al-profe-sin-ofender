public class ConsolaNotificador implements ObservadorJuego {
    @Override
    public void alNotificar(String mensaje) {
        System.out.println(" [EVENTO]: " + mensaje);
    }
}