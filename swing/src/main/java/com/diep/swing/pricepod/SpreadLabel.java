package com.diep.swing.pricepod;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class SpreadLabel extends JLabel {
    public SpreadLabel(String text){
        super(text);
        setOpaque(true);
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Font font = getFont();
        setFont(font.deriveFont(Font.BOLD, font.getSize()));
    }
}
