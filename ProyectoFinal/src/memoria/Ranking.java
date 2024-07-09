package memoria;

import java.util.ArrayList;
import java.util.List;

public class Ranking {
    private static List<String> jugadores = new ArrayList<>();

    public static void agregarJugador(String nombre, String tiempo) {
        jugadores.add(nombre + ": " + tiempo);
    }

    public static List<String> getRanking() {
        return jugadores;
    }
}
