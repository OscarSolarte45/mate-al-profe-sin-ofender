import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // ==============================
        // SINGLETON
        // ==============================

        GameManager gameManager = GameManager.getInstancia();
        // ==============================
        // ARMAS
        // ==============================

        List<Arma> armas = new ArrayList<>();
        armas.add(new Arma("Ladrillo", 45, 0.50));
        armas.add(new Arma("Piedra", 30, 0.65));
        armas.add(new Arma("Botella de vidrio", 40, 0.60));
        armas.add(new Arma("Cuchillo", 65, 0.55));
        armas.add(new Arma("Pistola", 90, 0.90));
        armas.add(new Arma("Cauchera", 25, 0.80));
        armas.add(new Arma("Extintor", 85, 0.30));
        armas.add(new Arma("Cosedora", 15, 0.70));
        armas.add(new Arma("Acido", 75, 0.45));

        // ==============================
        // BUCLE DE REINICIO DE JUEGO
        // ==============================

        boolean jugarNuevamente = true;

        while (jugarNuevamente) {
            // ==============================
            // PRESENTACIÓN
            // ==============================
            System.out.println("==========================================");
            System.out.println("       ¡BIENVENIDO A MATE AL PROFE!       ");
            System.out.println("==========================================");

            // ==============================
            // SELECCIÓN DEL PROFESOR
            // ==============================

            ProfesorFactory profesorFactory = null;

            while (profesorFactory == null) {
                System.out.println("\n--- REPERTORIO DE PROFESORES ---");
                System.out.println("1. Profe de Cálculo (Matemáticas - Salud: 150)");
                System.out.println("2. Profe de Programación (Sistemas - Salud: 100)");
                System.out.println("3. Profe de Física (Ciencias - Salud: 200)");
                System.out.print("\nSelecciona el profesor: ");

                try {
                    int opcionProfe = Integer.parseInt(scanner.nextLine());
                    switch (opcionProfe) {
                        case 1:
                            profesorFactory = new ProfesorCalculoFactory();
                            break;
                        case 2:
                            profesorFactory = new ProfesorProgramacionFactory();
                            break;
                        case 3:
                            profesorFactory = new ProfesorFisicaFactory();
                            break;
                        default:
                            System.out.println("Opción inválida. Intenta de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor ingresa un número válido.");
                }
            }

            // ==============================
            // FACTORY METHOD + BUILDER
            // ==============================

            Profesor profesor = profesorFactory.crearProfesor();
            // ==============================
            // SINGLETON + PARTIDA
            // ==============================
            Partida partida = gameManager.iniciarPartida(profesor);

            System.out.println("\nHas seleccionado a: " + profesor.getNombre());
            pausar(500);

            // ==============================
            // BUCLE PRINCIPAL
            // ==============================

            while (!profesor.seMurio()) {
                System.out.println("\n------------------------------------------");
                System.out.println("Profesor: " + profesor.getNombre());
                System.out.println("Materia: " + profesor.getMateria());
                System.out.println("Salud: " + profesor.getVidaActual() + "/" + profesor.getVidaMaxima());
                System.out.println("------------------------------------------");

                // ==============================
                // SELECCIÓN DEL ARMA
                // ==============================

                System.out.println("\nSelecciona tu arma:");
                for (int i = 0; i < armas.size(); i++) {
                    Arma arma = armas.get(i);
                    System.out.println((i + 1) + ". " + arma.getNombre() + " [Daño: " + arma.getGolpebase()
                            + " | Precisión: " + (int) (arma.getPrecision() * 100) + "%]");
                }
                System.out.print("Opción: ");

                int opcionArma = -1;
                try {
                    opcionArma = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    // Manejado por la siguiente validación
                }

                if (opcionArma < 0 || opcionArma >= armas.size()) {
                    System.out.println("Opción de arma inválida.");
                    continue;
                }

                Arma armaElegida = armas.get(opcionArma);
                gameManager.seleccionarArma(armaElegida);

                // ==============================
                // SELECCIÓN DE ZONA
                // ==============================

                System.out.println("\n¿A qué zona deseas apuntar?");
                System.out.println("1. Cabeza (Daño x2.0, Precisión 50%)");
                System.out.println("2. Torso (Daño x1.0, Precisión 90%)");
                System.out.println("3. Extremidades (Daño x0.6, Precisión 95%)");
                System.out.print("Opción: ");

                ZonaCuerpo zonaElegida = null;
                try {
                    int opcionZona = Integer.parseInt(scanner.nextLine());
                    switch (opcionZona) {
                        case 1:
                            zonaElegida = ZonaCuerpo.CABEZA;
                            break;
                        case 2:
                            zonaElegida = ZonaCuerpo.TORSO;
                            break;
                        case 3:
                            zonaElegida = ZonaCuerpo.EXTREMIDADES;
                            break;
                    }
                } catch (NumberFormatException e) {
                    // Manejado por la siguiente validación
                }

                if (zonaElegida == null) {
                    System.out.println("Zona inválida.");
                    continue;
                }

                // ==============================
                // ATAQUE
                // ==============================

                System.out.print("\n[!] Apuntando " + armaElegida.getNombre() + "...");
                pausar(600);

                partida.atacar(armaElegida, zonaElegida);
                pausar(600);
            }
            // ==============================
            // FIN DEL JUEGO
            // ==============================

            System.out.println("\n==========================================");
            System.out.println("¡HAS DERROTADO AL " + profesor.getNombre().toUpperCase() + "!");
            System.out.println("¡GANASTE LA MATERIA!");
            System.out.println("==========================================");

            // Menú de reinicio o salida
            boolean opcionValidaMenu = false;
            while (!opcionValidaMenu) {
                System.out.println("\n¿Qué deseas hacer ahora?");
                System.out.println("1. Jugar otra partida");
                System.out.println("2. Salir del juego");
                System.out.print("Opción: ");

                try {
                    int opcionSalida = Integer.parseInt(scanner.nextLine());
                    if (opcionSalida == 1) {
                        opcionValidaMenu = true;
                        System.out.println("\nReiniciando partida...\n");
                        pausar(800);
                    } else if (opcionSalida == 2) {
                        opcionValidaMenu = true;
                        jugarNuevamente = false;
                        System.out.println("\n¡Gracias por jugar! Saliendo del juego...");
                    } else {
                        System.out.println("Opción inválida. Elige 1 o 2.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor ingresa un número válido (1 o 2).");
                }
            }
        }

        scanner.close();
    }

    // ==============================
    // MÉTODO AUXILIAR DE TIEMPO
    // ==============================

    private static void pausar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}