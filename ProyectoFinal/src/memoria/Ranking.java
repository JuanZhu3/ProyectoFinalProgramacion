package memoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {
    private static List<Jugador> jugadores = new ArrayList<>();

    public static void agregarJugador(String nombre, int tiempo) {
        jugadores.add(new Jugador(nombre, tiempo));
        Collections.sort(jugadores);
    }

    public static List<Jugador> getRanking() {
        return jugadores;
    }

    public static void reiniciarRanking() {
        jugadores.clear();
    }

    public static class Jugador implements Comparable<Jugador> {
        String nombre;
        int tiempo;

        Jugador(String nombre, int tiempo) {
            this.nombre = nombre;
            this.tiempo = tiempo;
        }

        public int compareTo(Jugador otro) {
            return Integer.compare(this.tiempo, otro.tiempo);
        }
    }
}