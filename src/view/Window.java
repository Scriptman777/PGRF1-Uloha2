package view;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final Panel panel;
    private final JPanel menuPoly;
    private final JRadioButton radioNewPoly;
    private final JRadioButton radioFill;

    public Window() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("UHK FIM PGRF : " + this.getClass().getName());


        panel = new Panel();
        menuPoly = new JPanel();
        menuPoly.setLayout(new BoxLayout(menuPoly,BoxLayout.PAGE_AXIS));

        radioNewPoly = new JRadioButton("Polygon");
        menuPoly.add(radioNewPoly);
        radioFill = new JRadioButton("Fill");
        menuPoly.add(radioFill);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioNewPoly);
        bg.add(radioFill);




        add(panel, BorderLayout.CENTER);
        add(menuPoly, BorderLayout.WEST);
        setVisible(true);
        pack();

        setLocationRelativeTo(null);






    }



    public Panel getPanel() {
        return panel;
    }

    public JRadioButton getRadioFill() { return radioFill; }

    public JRadioButton getRadioNewPoly() { return radioNewPoly; }

    public JPanel getMenuPoly() { return menuPoly; }

}
