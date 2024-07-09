package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PantallaDescripcion extends JFrame {
    private JTextField nombreJugador;
    private Color colorFondo = new Color(230, 230, 250); // Lavanda claro
    private Color colorBoton = new Color(106, 90, 205); // Azul pizarra
    private Font fuenteTitulo = new Font("Arial", Font.BOLD, 28);
    private Font fuenteNormal = new Font("Arial", Font.PLAIN, 16);

    public PantallaDescripcion() {
        setTitle("Memoria - Descripción del Juego");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(230, 230, 250);
                Color color2 = new Color(216, 191, 216);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Juego de Memoria");
        titulo.setFont(fuenteTitulo);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descripcion = new JTextArea(
                "Bienvenido al Juego de Memoria!\n\n"
                        + "El objetivo es encontrar todos los pares de cartas idénticas.\n"
                        + "En cada turno, voltea dos cartas:\n"
                        + "- Si coinciden, ¡genial! Se quedan boca arriba.\n"
                        + "- Si no coinciden, se voltean de nuevo.\n\n"
                        + "El juego termina cuando encuentres todos los pares.\n"
                        + "¡Intenta completarlo en el menor tiempo posible!"
        );
        descripcion.setFont(fuenteNormal);
        descripcion.setEditable(false);
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        descripcion.setOpaque(false);
        descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel nombrePanel = new JPanel();
        nombrePanel.setOpaque(false);
        JLabel nombreLabel = new JLabel("Tu nombre:");
        nombreLabel.setFont(fuenteNormal);
        nombreJugador = new JTextField(15);
        nombreJugador.setFont(fuenteNormal);
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreJugador);

        JButton comenzarButton = new JButton("¡Comenzar el Juego!");
        comenzarButton.setFont(fuenteNormal);
        comenzarButton.setBackground(colorBoton);
        comenzarButton.setForeground(Color.WHITE);
        comenzarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        comenzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre;
                try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                    nombre = nombreJugador.getText().trim();
                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, ingresa tu nombre.", "Nombre requerido", JOptionPane.WARNING_MESSAGE);
                    } else {
                        new JuegoMemoria(nombre).setVisible(true);
                        dispose();
                    }
                } catch (Exception ex) {
                    System.err.println("Error al leer el nombre del jugador: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(descripcion);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(nombrePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(comenzarButton);
        panel.add(Box.createVerticalGlue());

        add(panel);
    }
}