package com.diep.swing;

import com.alee.laf.WebLookAndFeel;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerContrastIJTheme;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import java.awt.Dimension;

public class FlatLafDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> flatLaf());
    }

    public static void flatLaf() {
        try {
            //UIManager.setLookAndFeel(new FlatMaterialDarkerContrastIJTheme());
            WebLookAndFeel.install();
        } catch (Exception e) {
        }

        JToolBar toolBar = new JToolBar();
        toolBar.add(transparentButton("Orange"));
        toolBar.add(new JButton("Apple"));
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.add(transparentButton("Kiwi"));
        toolbarPanel.add(transparentButton("Guava"));
        toolBar.add(toolbarPanel);
        toolbarPanel.setOpaque(false);
        JPanel panel = new JPanel();
        panel.add(new JButton("One"));
        panel.add(new JButton("Two"));
        JPanel main = new JPanel();
        main.add(toolBar);
        main.add(transparentButton("Shell"));
        main.add(panel);
        JFrame frame = new JFrame();
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 200));
        frame.setVisible(true);
    }

    private static JButton transparentButton(String title) {
        JButton button = new JButton(title);
        button.setOpaque(false);
        button.setBorderPainted(false);
        return button;
    }
}
