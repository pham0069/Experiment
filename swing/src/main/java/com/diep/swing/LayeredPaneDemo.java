package com.diep.swing;

import com.jgoodies.forms.layout.CellConstraints;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class LayeredPaneDemo extends JFrame {
    public LayeredPaneDemo() {
        super("LayeredPane Demonstration");
        setSize(200, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLayeredPane lp = getLayeredPane();
        // Create 3 buttons
        JPanel top = new JPanel();
        top.setBackground(Color.white);
        top.setBounds(20, 20, 50, 50);
        JPanel middle = new JPanel();
        middle.setBackground(Color.gray);
        middle.setBounds(70, 20, 50, 50);
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.black);
        bottom.setBounds(65, 20, 10, 10);

        CellConstraints cc = new CellConstraints();
        // Place the buttons in different layers
        lp.add(middle, new Integer(2));
        lp.add(top, new Integer(3));
        lp.add(bottom, new Integer(5));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LayeredPaneDemo sl = new LayeredPaneDemo();
            sl.setVisible(true);
        });
    }
}