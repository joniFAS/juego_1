import java.util.*;

public class Baraja {

    Stack<Carta> cartas = new Stack<>();

    public Baraja() {

        String[] colores = {"Rojo", "Azul", "Verde", "Amarillo"};

        for (String c : colores) {

            cartas.push(new Carta(c, 0));

            for (int i = 1; i <= 9; i++) {
                cartas.push(new Carta(c, i));
                cartas.push(new Carta(c, i));
            }
        }

        Collections.shuffle(cartas);
    }

    public Carta robar() {

        if (cartas.empty()) {
            return null;
        }

        return cartas.pop();
    }
}