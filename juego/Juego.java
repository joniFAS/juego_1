import java.util.*;

public class Juego {

    Baraja baraja = new Baraja();
    Jugador jugador = new Jugador("Jugador");
    Jugador cpu = new Jugador("CPU");

    Stack<Carta> pila = new Stack<>();

    Scanner sc = new Scanner(System.in);

    public void iniciar() {

        for (int i = 0; i < 7; i++) {
            jugador.recibirCarta(baraja.robar());
            cpu.recibirCarta(baraja.robar());
        }

        pila.push(baraja.robar());

        while (true) {

            turnoJugador();

            if (jugador.mano.size() == 0) {
                System.out.println("¡¡GANASTE!!");
                break;
            }

            turnoCPU();

            if (cpu.mano.size() == 0) {
                System.out.println("GANO CPU");
                break;
            }
        }
    }

    void turnoJugador() {

        while (true) {

            System.out.println("\nCarta en mesa: " + pila.peek());
            System.out.println("Tus cartas:");

            jugador.mostrarCartas();

            System.out.println("Elige carta o escribe -1 para robar:");

            if (!sc.hasNextInt()) {
                System.out.println("Numero invalido, elija un numero valido");
                sc.next();
                continue;
            }

            int opcion = sc.nextInt();

            if (opcion == -1) {

                Carta nueva = baraja.robar();

                if (nueva == null) {
                    System.out.println("No hay cartas para robar");
                } else {
                    jugador.recibirCarta(nueva);
                    System.out.println("Robaste: " + nueva);
                }

                return;
            }

            if (opcion < 0 || opcion >= jugador.mano.size()) {
                System.out.println("Numero invalido, elija un numero valido");
                continue;
            }

            Carta elegida = jugador.mano.get(opcion);

            if (elegida.esValida(pila.peek())) {

                pila.push(elegida);
                jugador.mano.remove(opcion);

                return;

            } else {

                System.out.println("Carta no valida, intenta otra");
            }
        }
    }

    void turnoCPU() {

        System.out.println("\nTurno CPU");

        boolean jugo = false;

        for (int i = 0; i < cpu.mano.size(); i++) {

            Carta c = cpu.mano.get(i);

            if (c.esValida(pila.peek())) {

                System.out.println("CPU juega: " + c);

                pila.push(c);
                cpu.mano.remove(i);

                jugo = true;
                break;
            }
        }

        if (!jugo) {

            Carta robada = baraja.robar();

            if (robada != null) {

                cpu.recibirCarta(robada);
                System.out.println("CPU roba carta");

            } else {

                System.out.println("No hay cartas en la baraja");
            }
        }

        System.out.println("Cartas restantes CPU: " + cpu.mano.size());
    }
}