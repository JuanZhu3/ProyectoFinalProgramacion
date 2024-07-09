package memoria;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PanelJuego extends JPanel {
    private List<Tarjeta> tarjetas;
    private Tarjeta primeraTarjeta;
    private Tarjeta segundaTarjeta;
    private Timer timer;
    private int paresEncontrados;
    private boolean sePuedeVoltear = true;
    private JuegoMemoria juegoMemoria;

    public PanelJuego(JuegoMemoria juegoMemoria) {
        this.juegoMemoria = juegoMemoria;
        setLayout(new GridLayout(4, 5));
        tarjetas = new ArrayList<>();
        List<ImageIcon> imagenes = cargarImagenes();

        for (ImageIcon imagen : imagenes) {
            tarjetas.add(new Tarjeta(imagen, this));
            tarjetas.add(new Tarjeta(imagen, this));
        }

        Collections.shuffle(tarjetas);
        tarjetas.forEach(this::add);

        timer = new Timer(1000, e -> revisarPareja());
        paresEncontrados = 0;
    }

    private List<ImageIcon> cargarImagenes() {
        List<ImageIcon> imagenes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String path = "imagenes/imagen" + i + ".png";
            try {
                ImageIcon imagen = new ImageIcon(path);
                if (imagen.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    imagenes.add(new ImageIcon(imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
                } else {
                    System.err.println("No se pudo cargar la imagen: " + path);
                }
            } catch (Exception e) {
                System.err.println("Error al cargar la imagen: " + path);
                e.printStackTrace();
            }
        }
        return imagenes;
    }

    private void revisarPareja() {
        if (primeraTarjeta != null && segundaTarjeta != null) {
            if (!primeraTarjeta.esPar(segundaTarjeta)) {
                primeraTarjeta.voltear();
                segundaTarjeta.voltear();
            } else {
                primeraTarjeta.setEnabled(false);
                segundaTarjeta.setEnabled(false);
                paresEncontrados++;
                if (paresEncontrados == 10) {
                    juegoMemoria.terminarJuego();
                }
            }
            primeraTarjeta = null;
            segundaTarjeta = null;
            sePuedeVoltear = true;
        }
        timer.stop();
    }

    public void tarjetaSeleccionada(Tarjeta tarjeta) {
        if (!sePuedeVoltear) return;

        if (primeraTarjeta == null) {
            primeraTarjeta = tarjeta;
        } else if (segundaTarjeta == null && tarjeta != primeraTarjeta) {
            segundaTarjeta = tarjeta;
            sePuedeVoltear = false;
            timer.start();
        }
    }
}