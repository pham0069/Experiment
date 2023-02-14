package com.diep.swing.pricepod;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class OverlayLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                overlayCenterDemo();
            }
        });
    }

    public static void overlay1() {
        JPanel panel = createPanel();
        JFrame frame = createFrame();
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createPanel() {
        JPanel mainPanel = new JPanel(){
            @Override
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        mainPanel.setLayout(new OverlayLayout(mainPanel));

        JButton button = new JButton("Show Message");
        button.setAlignmentX(0.5f);
        button.setAlignmentY(0.5f);

        JPanel popupPanel = createPopupPanel(button);
        popupPanel.setAlignmentX(0.1f);
        popupPanel.setAlignmentY(0.1f);

        button.addActionListener(e -> {
            button.setEnabled(false);
            popupPanel.setVisible(true);
        });

        mainPanel.add(popupPanel);
        mainPanel.add(button);

        return mainPanel;
    }

    private static JPanel createPopupPanel(JComponent overlapComponent) {
        JPanel popupPanel = new JPanel(new BorderLayout());
        popupPanel.setOpaque(false);
        popupPanel.setMaximumSize(new Dimension(150, 70));
        popupPanel.setBorder(new LineBorder(Color.gray));
        popupPanel.setVisible(false);

        JLabel label = new JLabel("HI there!");
        popupPanel.add(wrapInPanel(label), BorderLayout.CENTER);

        JButton popupCloseButton = new JButton("Close");
        popupPanel.add(wrapInPanel(popupCloseButton), BorderLayout.SOUTH);

        popupCloseButton.addActionListener(e -> {
            overlapComponent.setEnabled(true);
            popupPanel.setVisible(false);
        });

        return popupPanel;
    }

    private static JPanel wrapInPanel(JComponent component) {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(50, 210, 250, 150));
        jPanel.add(component);
        return jPanel;
    }


    private static JFrame createFrame() {
        JFrame frame = new JFrame("OverlayLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(400, 300));
        return frame;
    }

    public static void overlayCenterDemo() {
        JFrame frame = new JFrame("Overlay Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        LayoutManager overlay = new OverlayLayout(panel);
        panel.setLayout(overlay);

        JButton button = new JButton("Small");
        button.setMaximumSize(new Dimension(50, 50));
        button.setBackground(Color.white);
        button.setAlignmentX(0.5f);
        button.setAlignmentY(0.5f);
        panel.add(button);

        button = new JButton("Medium");
        button.setMaximumSize(new Dimension(100, 100));
        button.setBackground(Color.gray);
        button.setAlignmentX(0.5f);
        button.setAlignmentY(0.5f);
        panel.add(button);

        button = new JButton("Large");
        button.setMaximumSize(new Dimension(200, 200));
        button.setBackground(Color.black);
        button.setAlignmentX(0.5f);
        button.setAlignmentY(0.5f);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public static void overlayDemo() {
        JFrame frame = new JFrame("Overlay Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        LayoutManager overlay = new OverlayLayout(panel);
        panel.setLayout(overlay);

        JButton button = new JButton("Small");
        button.setMaximumSize(new Dimension(50, 50));
        button.setBackground(Color.white);
        panel.add(button);

        button = new JButton("Medium");
        button.setMaximumSize(new Dimension(100, 100));
        button.setBackground(Color.gray);
        panel.add(button);

        button = new JButton("Large");
        button.setMaximumSize(new Dimension(200, 200));
        button.setBackground(Color.black);
        panel.add(button);

        frame.add(panel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }


}
