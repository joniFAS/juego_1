import java.util.*;

public class Jugador {

    String nombre;
    ArrayList<Carta> mano = new ArrayList<>();

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void recibirCarta(Carta c) {
        if (c != null) {
            mano.add(c);
        }
    }

    public void mostrarCartas() {

        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + " -> " + mano.get(i));
        }
    }

    public boolean tieneCartaValida(Carta cima) {

        for (Carta c : mano) {
            if (c.esValida(cima)) {
                return true;
            }
        }

        return false;
    }
}