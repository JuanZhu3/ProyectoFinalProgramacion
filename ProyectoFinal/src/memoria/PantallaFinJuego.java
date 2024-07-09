package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class PantallaFinJuego extends JFrame {
    private Color colorFondo = new Color(240, 248, 255);
    private Color colorBoton = new Color(70, 130, 180);
    private Font fuenteTitulo = new Font("Arial", Font.BOLD, 28);
    private Font fuenteNormal = new Font("Arial", Font.PLAIN, 16);
    private Font fuenteRanking = new Font("Courier New", Font.BOLD, 14);

    public PantallaFinJuego(String nombreJugador, int tiempo) {
        setTitle("¡Fin del Juego!");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Ranking.agregarJugador(nombreJugador, tiempo);
        List<Ranking.Jugador> ranking = Ranking.getRanking();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(240, 248, 255);
                Color color2 = new Color(176, 224, 230);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("¡Felicidades, " + nombreJugador + "!");
        titulo.setFont(fuenteTitulo);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel mensaje = new JLabel("Completaste el juego en " + tiempo + " segundos");
        mensaje.setFont(fuenteNormal);
        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea rankingArea = new JTextArea(10, 30);
        rankingArea.setEditable(false);
        rankingArea.setFont(fuenteRanking);
        rankingArea.setOpaque(false);
        StringBuilder rankingTexto = new StringBuilder("Ranking:\n\n");
        for (int i = 0; i < ranking.size(); i++) {
            Ranking.Jugador jugador = ranking.get(i);
            rankingTexto.append(String.format("%d. %-15s %5d segundos\n", i + 1, jugador.nombre, jugador.tiempo));
        }
        rankingArea.setText(rankingTexto.toString());

        JScrollPane scrollPane = new JScrollPane(rankingArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        botonesPanel.setOpaque(false);

        JButton salirButton = crearBoton("Salir");
        salirButton.addActionListener(e -> System.exit(0));

        JButton jugarDeNuevoButton = crearBoton("Jugar de nuevo");
        jugarDeNuevoButton.addActionListener(e -> {
            new PantallaDescripcion().setVisible(true);
            dispose();
        });

        botonesPanel.add(jugarDeNuevoButton);
        botonesPanel.add(salirButton);

        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(mensaje);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(scrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(botonesPanel);

        add(panel);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(fuenteNormal);
        boton.setBackground(colorBoton);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(150, 40));
        return boton;
    }
}