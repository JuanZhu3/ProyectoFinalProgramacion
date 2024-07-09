package memoria;

import javax.swing.*;
import java.awt.*;

public class JuegoMemoria extends JFrame {
    private String nombreJugador;
    private Temporizador temporizador;
    private PanelJuego panelJuego;

    public JuegoMemoria(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        setTitle("Juego de Memoria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        iniciarJuego();
    }

    private void iniciarJuego() {
        setLayout(new BorderLayout());
        temporizador = new Temporizador();
        panelJuego = new PanelJuego(this);
        add(panelJuego, BorderLayout.CENTER);
        add(temporizador, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel();
        JButton reintentarButton = new JButton("Reintentar");
        reintentarButton.addActionListener(e -> reiniciarJuego());
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(e -> System.exit(0));
        botonesPanel.add(reintentarButton);
        botonesPanel.add(salirButton);
        add(botonesPanel, BorderLayout.SOUTH);
    }

    public void reiniciarJuego() {
        remove(panelJuego);
        remove(temporizador);
        iniciarJuego();
        revalidate();
        repaint();
    }

    public void terminarJuego() {
        int tiempoTotal = temporizador.getTiempo();
        SwingUtilities.invokeLater(() -> {
            new PantallaFinJuego(nombreJugador, tiempoTotal).setVisible(true);
            dispose();
        });
    }
}