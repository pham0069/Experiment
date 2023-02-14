package com.diep.swing.layout;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

public class FormLayoutDemo {

    public static void addComponentsToPane(Container pane) {
        JButton button;
        pane.setLayout(new FormLayout("100dlu, 100dlu","20dlu"));
        CellConstraints cc = new CellConstraints();

        button = new JButton("Button 1");
        Dimension d = new Dimension(50, 20);
        /*
        button.setPreferredSize(d);
        button.setSize(d);
        button.setMinimumSize(d);
        button.setMaximumSize(d); */
        JPanel panel = new JPanel();
        panel.add(button);
        pane.add(panel, cc.xy(1, 1));

        JLabel label= new JLabel("     Button 2");
        JPanel labelPanel = new JPanel();
        labelPanel.add(label);
        labelPanel.setBackground(Color.ORANGE);
        labelPanel.setForeground(Color.WHITE);
        pane.add(labelPanel, cc.xy(2, 1));
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FormLayoutDemo");
        frame.setMinimumSize(new Dimension(200, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
