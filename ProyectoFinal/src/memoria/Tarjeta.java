package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tarjeta extends JButton {
    private ImageIcon imagen;
    private boolean estaVolteada;
    private PanelJuego panelJuego;

    public Tarjeta(ImageIcon imagen, PanelJuego panelJuego) {
        this.imagen = imagen;
        this.panelJuego = panelJuego;
        estaVolteada = false;
        setIcon(new ImageIcon(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB)));
        setPreferredSize(new Dimension(100, 100));
        addActionListener(e -> {
            if (!estaVolteada) {
                voltear();
                panelJuego.tarjetaSeleccionada(this);
            }
        });
    }

    public void voltear() {
        if (estaVolteada) {
            setIcon(new ImageIcon(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB)));
        } else {
            setIcon(imagen);
        }
        estaVolteada = !estaVolteada;
    }

    public boolean esPar(Tarjeta otraTarjeta) {
        return this.imagen == otraTarjeta.imagen;
    }
}