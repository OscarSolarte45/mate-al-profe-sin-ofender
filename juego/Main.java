import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static GameManager gameManager;
    private static List<Arma> armas;
    private static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        gameManager = GameManager.getInstancia();
        armas = crearArmas();

        mostrarPresentacion();
        boolean jugarNuevamente = confirmarInicioPartida();

        while (jugarNuevamente) {
            ProfesorFactory profesorFactory = seleccionarProfesorFactory();
            Profesor profesor = profesorFactory.crearProfesor();
            gameManager.iniciarPartida(profesor);

            Partida partida = new Partida(profesor);
            System.out.println("\nElegiste a: " + profesor.getNombre());
            pausar(500);

            ejecutarPartida(partida);
            mostrarResultadoFinal();

            jugarNuevamente = preguntarSiJugarOtraVez();
        }
        scanner.close();
    }
    private static List<Arma> crearArmas() {
        List<Arma> lista = new ArrayList<>();
        lista.add(new Arma("Ladrillo", 45, 0.50));
        lista.add(new Arma("Piedra", 30, 0.65));
        lista.add(new Arma("Botella de vidrio", 40, 0.60));
        lista.add(new Arma("Cuchillo", 65, 0.55));
        lista.add(new Arma("Pistola", 90, 0.90));
        lista.add(new Arma("Cauchera", 25, 0.80));
        lista.add(new Arma("Extintor", 85, 0.30));
        lista.add(new Arma("Cosedora", 15, 0.70));
        lista.add(new Arma("Acido", 75, 0.45));
        return lista;
    }
    private static void mostrarPresentacion() {
        System.out.println("------------------------------------------");
        System.out.println("       ¡BIENVENIDO A MATE AL PROFE!       ");
        System.out.println("------------------------------------------");
    }
    private static boolean confirmarInicioPartida() {
        while (true) {
            System.out.println("¿Deseas iniciar una nueva partida?");
            System.out.println("1. Iniciar partida");
            System.out.println("2. Salir");
            System.out.print("Opcion: ");
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion == 1) {
                    return true;
                } else if (opcion == 2) {
                    return false;
                } else {
                    System.out.println("Error: Opcion invalida. Elige 1 o 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingresa un numero valido (1 o 2)");
            }
        }
    }
    private static ProfesorFactory seleccionarProfesorFactory() {
        ProfesorFactory profesorFactory = null;
        while (profesorFactory == null) {
            System.out.println("\n--- VICTIMAS ---");
            System.out.println("1. Profe de Calculo (Calculo Diferencial - Salud: 150)");
            System.out.println("2. Profe de Programacion (POO - Salud: 100)");
            System.out.println("3. Profe de Fisica (Fisica Mecanica - Salud: 200)");
            System.out.print("\nElige tu victima: ");
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
                        System.out.println("Error: Opcion invalida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingresa un numero valido.");
            }
        }
        return profesorFactory;
    }
    private static void ejecutarPartida(Partida partida) {
        Profesor profesor = gameManager.getProfesorActual();
        while (!profesor.seMurio()) {
            mostrarEstadoProfesor(profesor);
            Arma armaElegida = seleccionarArma();
            if (armaElegida == null) {
                System.out.println("Error: No puedes equipar este arma.");
                continue;
            }
            gameManager.seleccionarArma(armaElegida);
            ZonaCuerpo zonaElegida = seleccionarZona();
            if (zonaElegida == null) {
                System.out.println("Error: Elige una Zona valida.");
                continue;
            }
            Arma armaParaAtacar = gameManager.getArmaActual();

            System.out.print("\nApuntando Con " + armaParaAtacar.getNombre() + "...");
            pausar(600);

            partida.atacar(armaParaAtacar, zonaElegida);
            pausar(600);
        }
    }
    private static void mostrarEstadoProfesor(Profesor profesor) {
        System.out.println("\n------------------------------------------");
        System.out.println("Profesor: " + profesor.getNombre());
        System.out.println("Materia: " + profesor.getMateria());
        System.out.println("Vida: " + profesor.getVidaActual() + "/" + profesor.getVidaMaxima());
        System.out.println("------------------------------------------");
    }
    private static Arma seleccionarArma() {
        System.out.println("\nElige tu arma:");
        for (int i = 0; i < armas.size(); i++) {
            Arma arma = armas.get(i);
            System.out.println((i + 1)+ ". "+ arma.getNombre()+ " [Daño: "+ arma.getGolpebase()+ 
            " | Precision: "+ (int) (arma.getPrecision() * 100)+ "%]");
        }
        System.out.print("Opcion: ");
        int opcionArma;
        try {
            opcionArma = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return null;
        }
        if (opcionArma < 0 || opcionArma >= armas.size()) {
            return null;
        }
        return armas.get(opcionArma);
    }
    private static ZonaCuerpo seleccionarZona() {
        System.out.println("\n¿A donde le quieres pegar?");
        System.out.println("1. Cabeza (Daño x2.0, Precision 50%)");
        System.out.println("2. Torso (Daño x1.0, Precision 90%)");
        System.out.println("3. Extremidades (Daño x0.6, Precision 95%)");
        System.out.print("Opcion: ");

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
            return null;
        }
        return zonaElegida;
    }
    private static void mostrarResultadoFinal() {
        Profesor profesor = gameManager.getProfesorActual();
        System.out.println("\n------------------------------------------");
        System.out.println("¡MATASTE AL " + profesor.getNombre().toUpperCase() + "!");
        System.out.println("AHORA EN QUE ESTRELLA ESTARA..");
        System.out.println("------------------------------------------");
    }
    private static boolean preguntarSiJugarOtraVez() {
        boolean opcionValidaMenu = false;
        boolean jugarNuevamente = true;
        while (!opcionValidaMenu) {
            System.out.println("\n¿Que quieres hacer?");
            System.out.println("1. Jugar otra partida");
            System.out.println("2. Salir del juego");
            System.out.print("Opcion: ");

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
                    System.out.println("Error: Opcion invalida. Elige 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingresa un numero valido (1 o 2).");
            }
        }
        return jugarNuevamente;
    }
    private static void pausar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}