package com.diep.swing.pricepod;

import com.google.common.collect.Lists;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Component;
import java.util.List;
import java.util.Vector;

public class CurrencyPairComboBox extends JComboBox {
    private static final List<String> ccyPairs = Lists.newArrayList("EUR/USD", "USD/JPY", "AUD/USD", "AUD/JPY");
    public CurrencyPairComboBox() {
        super(new Vector(ccyPairs));
        for (Component component : getComponents()) {
            if (component instanceof JButton) {
                remove(component);
            }
        }
    }
}
