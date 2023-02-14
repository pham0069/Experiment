package com.diep.swing.pricepod;

import javax.swing.JButton;
import java.awt.Font;

public class PriceButton extends JButton {
    private String price;
    private String source;
    private Font prefixFont, largeFont, remainderFont;

    public PriceButton(String price) {
        super(price);
        this.price = price;
        setBlueTheme();
    }
    protected void updateFonts() {
        Font title = getFont();
        prefixFont = title.deriveFont(Font.BOLD, getFont().getSize2D()+1);
        largeFont = title.deriveFont(Font.BOLD, getFont().getSize2D()+15);
        remainderFont = title.deriveFont(Font.BOLD, getFont().getSize2D());
    }
    void setBlueTheme() {
        setOpaque(true);
        //setBackground(Color.BLUE);
        //setForeground(Color.WHITE);
        setBounds(10, 10, 100, 20);
        setFont(getFont().deriveFont(Font.BOLD, 20));
    }
}
