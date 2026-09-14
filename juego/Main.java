import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ==============================
        // OBSERVER
        // ==============================

        ObservadorJuego notificador = new ConsolaNotificador();

        // ==============================
        // STRATEGY
        // ==============================

        EstrategiaAtaque estrategiaAtaque = new AtaqueEstandar();

        // ==============================
        // ARMAS DISPONIBLES
        // ==============================

        List<Arma> armas = new ArrayList<>();

        armas.add(
                new Arma(
                        "Borrador Pesado",
                        20,
                        0.90
                )
        );

        armas.add(
                new Arma(
                        "Regla de Metal",
                        35,
                        0.75
                )
        );

        armas.add(
                new Arma(
                        "Proyector Viejo",
                        60,
                        0.50
                )
        );


        System.out.println("==========================================");
        System.out.println("     ¡BIENVENIDO A MATA AL PROFE!        ");
        System.out.println("==========================================\n");

        // ==============================
        // FACTORY METHOD
        // ==============================

        System.out.println("--- REPERTORIO DE PROFESORES ---");

        System.out.println(
                "1. Profe de Cálculo (Matemáticas - Salud: 150)"
        );

        System.out.println(
                "2. Profe de Programación (Sistemas - Salud: 100)"
        );

        System.out.println(
                "3. Profe de Física (Ciencias - Salud: 200)"
        );

        System.out.print(
                "\nSelecciona el número del profesor a enfrentar: "
        );

        int opcionProfe = scanner.nextInt();

        ProfesorFactory profesorFactory;

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
                System.out.println(
                        "Opción inválida. Se seleccionará el profesor de Cálculo."
                );

                profesorFactory = new ProfesorCalculoFactory();
                break;
        }

        Profesor profeObjetivo = profesorFactory.crearProfesor();

        notificador.alNotificar(
                "Has seleccionado enfrentar a: "
                + profeObjetivo.getNombre()
        );

        // ==============================
        // BUCLE PRINCIPAL
        // ==============================

        while (!profeObjetivo.seMurio()) {

            System.out.println("\n------------------------------------------");

            System.out.println(
                    "Salud actual del profesor: "
                    + profeObjetivo.getVidaActual()
                    + "/"
                    + profeObjetivo.getVidaMaxima()
            );

            System.out.println("------------------------------------------");

            // ==============================
            // SELECCIÓN DE ARMA
            // ==============================

            System.out.println("Selecciona tu arma:");

            for (int i = 0; i < armas.size(); i++) {

                Arma a = armas.get(i);

                System.out.println(
                        (i + 1)
                        + ". "
                        + a.getNombre()
                        + " [Daño: "
                        + a.getGolpebase()
                        + " | Precisión: "
                        + (int) (a.getPrecision() * 100)
                        + "%]"
                );
            }

            System.out.print("Opción: ");

            int opcionArma = scanner.nextInt() - 1;

            // Validación básica del arma
            if (opcionArma < 0 || opcionArma >= armas.size()) {

                System.out.println(
                        "Opción de arma inválida."
                );

                continue;
            }

            Arma armaElegida = armas.get(opcionArma);

            // ==============================
            // SELECCIÓN DE ZONA
            // ==============================

            System.out.println(
                    "\n¿A qué parte del cuerpo vas a apuntar?"
            );

            System.out.println(
                    "1. Cabeza (Daño x2.0, Precisión 50%)"
            );

            System.out.println(
                    "2. Torso (Daño x1.0, Precisión 90%)"
            );

            System.out.println(
                    "3. Extremidades (Daño x0.6, Precisión 95%)"
            );

            System.out.print("Opción: ");

            int opcionZona = scanner.nextInt();

            ZonaCuerpo zonaElegida;

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

                default:
                    System.out.println(
                            "Zona inválida. Se seleccionará el torso."
                    );

                    zonaElegida = ZonaCuerpo.TORSO;
                    break;
            }

            // ==============================
            // EJECUCIÓN DEL ATAQUE
            // ==============================

            estrategiaAtaque.ejecutarAtaque(
                    armaElegida,
                    zonaElegida,
                    profeObjetivo,
                    notificador
            );

            // ==============================
            // DERROTA
            // ==============================

            if (profeObjetivo.seMurio()) {

                notificador.alNotificar(
                        " ¡HAS DERROTADO AL "
                        + profeObjetivo.getNombre().toUpperCase()
                        + "! Ganaste la materia. "
                );
            }
        }

        scanner.close();
    }
}