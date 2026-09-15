import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameManager gameManager = GameManager.getInstancia();

        List<Arma> armas = new ArrayList<>();
        armas.add(new Arma("Ladrillo",45,0.50));
        armas.add(new Arma("Piedra",30,0.65));
        armas.add(new Arma("Botella de vidrio",40,0.60));
        armas.add(new Arma("Cuchillo",65,0.55));
        armas.add(new Arma("Pistola",90,0.90));
        armas.add(new Arma("Cauchera",25,0.80));
        armas.add(new Arma("Extintor",85,0.30));
        armas.add(new Arma("Cosedora",15,0.70));
        armas.add(new Arma("Acido",75,0.45));
        
        System.out.println("==========================================");
        System.out.println("       ¡BIENVENIDO A MATE AL PROFE!       ");
        System.out.println("==========================================");

        System.out.println("\n--- REPERTORIO DE PROFESORES ---");

        System.out.println( "1. Profe de Cálculo (Matemáticas - Salud: 150)");

        System.out.println("2. Profe de Programación (Sistemas - Salud: 100)");

        System.out.println("3. Profe de Física (Ciencias - Salud: 200)");

        System.out.print("\nSelecciona el profesor: ");

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
                System.out.println("Opción inválida.");
                scanner.close();
                return;
        }

        Profesor profesor = profesorFactory.crearProfesor();

        gameManager.iniciarPartida(profesor);

        Partida partida = new Partida(profesor);

        System.out.println("\nHas seleccionado a: "+ profesor.getNombre());

        while (!profesor.seMurio()) {

            System.out.println("\n------------------------------------------");

            System.out.println("Profesor: "+ profesor.getNombre());

            System.out.println("Materia: "+ profesor.getMateria());

            System.out.println("Salud: "+ profesor.getVidaActual()+ "/"+ profesor.getVidaMaxima());

            System.out.println("------------------------------------------");

            System.out.println("\nSelecciona tu arma:");

            for (int i = 0; i < armas.size(); i++) {
                Arma arma = armas.get(i);
                System.out.println((i + 1)+ ". "+ arma.getNombre()+ " [Daño: "+ arma.getGolpebase()
                        + " | Precisión: "+ (int) (arma.getPrecision() * 100)+ "%]");
            }
            System.out.print("Opción: ");

            int opcionArma = scanner.nextInt() - 1;

            if (opcionArma < 0 || opcionArma >= armas.size()) {

                System.out.println("Opción de arma inválida.");

                continue;
            }

            Arma armaElegida = armas.get(opcionArma);

            gameManager.seleccionarArma(armaElegida);

            System.out.println("\n¿A qué zona deseas apuntar?");

            System.out.println("1. Cabeza (Daño x2.0, Precisión 50%)");

            System.out.println("2. Torso (Daño x1.0, Precisión 90%)");

            System.out.println("3. Extremidades (Daño x0.6, Precisión 95%)");

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

                default: System.out.println("Zona inválida.");

                    continue;
            }

            partida.atacar(armaElegida,zonaElegida);
        }

        System.out.println("\n==========================================");

        System.out.println("¡HAS DERROTADO AL "+ profesor.getNombre().toUpperCase()+ "!");

        System.out.println("¡GANASTE LA MATERIA!");

        System.out.println("==========================================");

        scanner.close();
    }
}