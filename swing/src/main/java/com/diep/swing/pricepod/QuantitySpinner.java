package com.diep.swing.pricepod;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class QuantitySpinner extends JSpinner {
    public QuantitySpinner() {
        super(new SpinnerNumberModel(0, 0, 1000000000, 1000));
    }
}
