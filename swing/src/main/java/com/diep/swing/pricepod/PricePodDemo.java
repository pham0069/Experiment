package com.diep.swing.pricepod;

import com.alee.laf.WebLookAndFeel;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PricePodDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> pricePodDemo());
    }

    public static void pricePodDemo() {
        try {
            UIManager.setLookAndFeel(new FlatMaterialDarkerContrastIJTheme());
            //WebLookAndFeel.install();
        } catch (UnsupportedLookAndFeelException e) {
            WebLookAndFeel.install();
        }

        PricePod pricePod = new PricePod();
        JFrame frame = new JFrame();
        frame.setContentPane(pricePod);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
