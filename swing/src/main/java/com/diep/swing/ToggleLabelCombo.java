package com.diep.swing;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.CardLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToggleLabelCombo extends JPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            JFrame frame = new JFrame("Toggle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Create and set up the content pane.
            JComponent newContentPane = new ToggleLabelCombo();
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(newContentPane);

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        });
    }
    private static final String LABEL = "LABEL";
    private static final String COMBO = "COMBO";
    private final JComboBox comboBox;
    private final JLabel label;
    private final CardLayout cardLayout;
    private String currentSelectedCard;

    public ToggleLabelCombo() {
        this.comboBox = new JComboBox(new Integer[]{1, 2, 3, 4, 5});
        this.label = new JLabel("abc");
        this.cardLayout = new CardLayout();

        setLayout(cardLayout);
        add(label, LABEL);
        add(comboBox, COMBO);
        show(LABEL);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isShown(LABEL)) {
                    show(COMBO);
                }
            }
        });
        comboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isShown(COMBO)) {
                    show(LABEL);
                }
            }
        });
    }

    private void show(String cardName) {
        cardLayout.show(this, cardName);
        currentSelectedCard = cardName;
    }

    private boolean isShown(String cardName) {
        return currentSelectedCard.equals(cardName);
    }

}
