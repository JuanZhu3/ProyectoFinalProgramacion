package memoria;

import javax.swing.*;

public class PantallaJuego extends JFrame {
    public PantallaJuego(String nombre) {
        JuegoMemoria juegoMemoria = new JuegoMemoria(nombre);
        juegoMemoria.setVisible(true);
        dispose();
    }
}