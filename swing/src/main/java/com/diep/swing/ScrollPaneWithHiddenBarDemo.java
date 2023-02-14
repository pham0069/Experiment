package com.diep.swing;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * https://stackoverflow.com/questions/2445297/detecting-mouse-enter-exit-events-anywhere-on-jpanel/16554233#16554233
 */
public class ScrollPaneWithHiddenBarDemo extends JFrame {

    JScrollPane scrollPane;
    ScrollPaneMouseHandler scrollPaneHandler;

    public ScrollPaneWithHiddenBarDemo() {
        super("JScrollPane Demonstration");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    public void init() {
        JViewport viewport = new JViewport();
        viewport.setView(createContent());
        scrollPane = new JScrollPane();
        scrollPane.setViewport(viewport);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneHandler = new ScrollPaneMouseHandler(this, this.scrollPane);
        Toolkit.getDefaultToolkit().addAWTEventListener(scrollPaneHandler, AWTEvent.MOUSE_EVENT_MASK);


        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(new JLabel("EAST"), BorderLayout.EAST);
        getContentPane().add(new JLabel("WEST"), BorderLayout.WEST);
        getContentPane().add(new JLabel("SOUTH"), BorderLayout.SOUTH);
        getContentPane().add(new JLabel("NORTH"), BorderLayout.NORTH);
    }

    private JPanel createContent() {
        JRadioButton form[][] = new JRadioButton[12][5];
        String counts[] = { "", "0-1", "2-5", "6-10", "11-100", "101+" };
        String categories[] = { "Household", "Office", "Extended Family",
                "Company (US)", "Company (World)", "Team", "Will",
                "Birthday Card List", "High School", "Country", "Continent",
                "Planet" };
        JPanel p = new JPanel();
        p.setSize(400, 400);
        p.setLayout(new GridLayout(13, 6, 10, 0));
        for (int row = 0; row < 13; row++) {
            ButtonGroup bg = new ButtonGroup();
            for (int col = 0; col < 6; col++) {
                if (row == 0) {
                    p.add(new JLabel(counts[col]));
                } else {
                    if (col == 0) {
                        p.add(new JLabel(categories[row - 1]));
                    } else {
                        form[row - 1][col - 1] = new JRadioButton();
                        bg.add(form[row - 1][col - 1]);
                        p.add(form[row - 1][col - 1]);
                    }
                }
            }
        }
        return p;
    }

    @Override
    public void dispose() {
        super.dispose();
        Toolkit.getDefaultToolkit().removeAWTEventListener(scrollPaneHandler);
    }

    /**
     * Tried this but did not work
     * When mouse hover over button inside scrollpane bounds, exit event is fired
     * This is because buttons obscure the scrollpane itself
     * Also sometimes when mouse exits the scrollpane bound, exit event is not fired. Why?
     * @param scrollPane
     */
    private void addMouseListener(JScrollPane scrollPane) {
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                System.out.println("Entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (scrollPane.contains(e.getPoint())) {
                    return;
                }
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                System.out.println("Exited");
            }
        };
        scrollPane.addMouseListener(listener);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new ScrollPaneWithHiddenBarDemo();
        });
    }
    public static class ScrollPaneMouseHandler implements AWTEventListener {

        private Component parent;
        private JScrollPane scrollPane;
        private boolean hasExited = true;

        public ScrollPaneMouseHandler(Component parent, JScrollPane scrollPane) {
            this.parent = parent;
            this.scrollPane = scrollPane;
        }

        @Override
        public void eventDispatched(AWTEvent e) {
            if (e instanceof MouseEvent) {
                if (SwingUtilities.isDescendingFrom((Component) e.getSource(), parent)) {
                    MouseEvent m = (MouseEvent) e;
                    if (m.getID() == MouseEvent.MOUSE_ENTERED) {
                        if (hasExited) {
                            System.out.println("Entered");
                            hasExited = false;
                            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        }
                    } else if (m.getID() == MouseEvent.MOUSE_EXITED) {
                        Point p = SwingUtilities.convertPoint(
                                (Component) e.getSource(),
                                m.getPoint(), scrollPane);
                        if (!scrollPane.getBounds().contains(p)) {
                            System.out.println("Exited");
                            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                            hasExited = true;
                        }
                    }
                }
            }
        }
    }

    public static class TargetedMouseHandler implements AWTEventListener {

        private Component parent;
        private Component innerBound;
        private boolean hasExited = true;

        public TargetedMouseHandler(Component p, Component p2) {
            parent = p;
            innerBound = p2;
        }

        @Override
        public void eventDispatched(AWTEvent e) {
            if (e instanceof MouseEvent) {
                if (SwingUtilities.isDescendingFrom((Component) e.getSource(), parent)) {
                    MouseEvent m = (MouseEvent) e;
                    if (m.getID() == MouseEvent.MOUSE_ENTERED) {
                        if (hasExited) {
                            System.out.println("Entered");
                            hasExited = false;
                        }
                    } else if (m.getID() == MouseEvent.MOUSE_EXITED) {
                        Point p = SwingUtilities.convertPoint(
                                (Component) e.getSource(),
                                m.getPoint(),
                                innerBound);
                        if (!innerBound.getBounds().contains(p)) {
                            System.out.println("Exited");
                            hasExited = true;
                        }
                    }
                }
            }
        }
    }
}