package com.diep.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

public class ScrollPaneWithResizeContentDemo {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    JFrame myFrame = new JFrame("Demo");
                    JPanel sideBar = new JPanel();
                    JPanel centerPanel = new JPanel();
                    centerPanel.add(new JLabel("This is the center panel."));

                    JPanel buttonContainer = new ScrollablePanel();
                    JButton myButton = null;

                    for (int i = 0; i < 20; i++) {
                        buttonContainer.setLayout(new GridLayout(20, 1, 0, 0));
                        myButton = new JButton("This is my button nr. " + i);
                        buttonContainer.add(myButton);
                    }

                    sideBar.setLayout(new BorderLayout(0, 0));

                    JScrollPane scrollPane = new JScrollPane(buttonContainer);

                    sideBar.add(scrollPane);

                    myFrame.getContentPane().add(sideBar, BorderLayout.WEST);
                    myFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);

                    myFrame.setLocationByPlatform(true);
                    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    myFrame.pack();
                    myFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static class ScrollablePanel extends JPanel implements Scrollable {

        public Dimension getPreferredSize() {
            return getPreferredScrollableViewportSize();
        }

        public Dimension getPreferredScrollableViewportSize() {
            if (getParent() == null)
                return getSize();
            Dimension d = getParent().getSize();
            int c = (int) Math
                    .floor((d.width - getInsets().left - getInsets().right) / 50.0);
            if (c == 0)
                return d;
            int r = 20 / c;
            if (r * c < 20)
                ++r;
            return new Dimension(c * 50, r * 50);
        }

        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 50;
        }

        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 10;
        }

        public boolean getScrollableTracksViewportHeight() {
            return false;
        }

        public boolean getScrollableTracksViewportWidth() {
            return getParent() != null ? getParent().getSize().width > getPreferredSize().width
                    : true;
        }
    }
}
