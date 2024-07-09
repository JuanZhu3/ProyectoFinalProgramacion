package memoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador extends JPanel {
    private JLabel labelTiempo;
    private Timer timer;
    private int tiempo;

    public Temporizador() {
        labelTiempo = new JLabel("Tiempo: 0");
        add(labelTiempo);
        tiempo = 0;

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tiempo++;
                labelTiempo.setText("Tiempo: " + tiempo);
            }
        });
        timer.start();
    }

    public int getTiempo() {
        return tiempo;
    }
}