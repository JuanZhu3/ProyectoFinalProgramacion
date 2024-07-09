package memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PantallaInicio extends JFrame {
    public PantallaInicio() {
        setTitle("Pantalla de Inicio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel informacion = new JLabel("<html><div style='text-align: center;'>"
                + "<span style='font-size: 15px;'>Universidad Tecnológica de Panamá<br>"
                + "Facultad de Sistemas Computacionales<br>"
                + "Ingeniería de Software<br>"
                + "1SF123<br><br>"
                + "Integrantes:<br>"
                + "Juan Zhu - 8-1010-701<br>"
                + "Jeremy Martínez - 8-1024-14701<br>"
                + "Rafael Gómez - 8-1011-1757<br>"
                + "Alex De Bouaud - 8-1015-1644<br><br>"
                + "Profesor: Rodrigo Yángüez<br>"
                + "12/07/2024<br><br>"
                + "</span></div></html>", JLabel.CENTER);
        panel.add(informacion, BorderLayout.CENTER);

        JPanel logosPanel = new JPanel();
        logosPanel.setLayout(new BorderLayout());

        try {
            URL urlLogoUni = new URL("https://utp.ac.pa/documentos/2015/imagen/logo_utp_1_72.png");
            ImageIcon iconUni = new ImageIcon(urlLogoUni);
            Image imageUni = iconUni.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel logoUni = new JLabel(new ImageIcon(imageUni));
            logoUni.setPreferredSize(new Dimension(100, 100));
            logosPanel.add(logoUni, BorderLayout.WEST);

            URL urlLogoFac = new URL("https://fisc.utp.ac.pa/sites/fisc.utp.ac.pa/files/documentos/2020/imagen/logo_en_contactenos.png");
            ImageIcon iconFac = new ImageIcon(urlLogoFac);
            Image imageFac = iconFac.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel logoFac = new JLabel(new ImageIcon(imageFac));
            logoFac.setPreferredSize(new Dimension(100, 100));
            logosPanel.add(logoFac, BorderLayout.EAST);
        } catch (Exception e) {
            e.printStackTrace();
        }

        panel.add(logosPanel, BorderLayout.NORTH);

        JButton avanzarButton = new JButton("Avanzar");
        avanzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaDescripcion descripcion = new PantallaDescripcion();
                descripcion.setVisible(true);
                dispose();
            }
        });
        panel.add(avanzarButton, BorderLayout.SOUTH);

        add(panel);
    }
}