package com.diep.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TooltipInSwing extends JFrame {
    public TooltipInSwing() {
        super("TooltipInSwing");
        setSize(400, 300);
        getContentPane().setLayout(new FlowLayout());

        // globally
        UIManager.put("ToolTip.font",
                new FontUIResource("SansSerif", Font.BOLD, 18));
        JButton b1 = new JButton("tooltip 1");
        System.out.println(b1.getFont().getSize() + b1.getFont().getFontName());
        b1.setToolTipText("tool tip sansserif bold");

        getContentPane().add(b1);

        // only one
        String html =
                "<html>" +
                        "<text><font size=\"13px\" >1.12</font></text>" +
                        "<text><font size=\"25\" >34</font></text>" +
                        "<text><font size=\"100\" >56</font></text>" +
                        "</html>";
        JButton b2 = new JButton(html);
        b2.setToolTipText(html);

        getContentPane().add(b2);

        WindowListener wndCloser = new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);
        setVisible(true);
    }

    public static void main(String args[]){
        new TooltipInSwing();
    }
}