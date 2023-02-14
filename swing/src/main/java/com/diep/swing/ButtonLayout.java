package com.diep.swing;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ButtonLayout extends JPanel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            JFrame frame = new JFrame("Toggle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Create and set up the content pane.
            JComponent newContentPane = new ButtonLayout();
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(newContentPane);

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        });
    }

    private final JButton button;

    public ButtonLayout() {
        this.button = new JButton();
        button.setLayout(new FormLayout("d,d,d", "d,d,d"));
        CellConstraints cc = new CellConstraints();
        button.add(new JLabel("Buy EUR"), cc.xy(1, 1));
        button.add(new JLabel("IVT"), cc.xy(3, 1));
        button.add(new JLabel("1.2345"), cc.xy(2, 2));
        button.add(new JLabel("(1.2)"), cc.xy(2, 3));

        add(button);
    }
}
