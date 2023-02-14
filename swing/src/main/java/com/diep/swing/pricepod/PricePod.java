package com.diep.swing.pricepod;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

public class PricePod extends JPanel {
    private final CurrencyPairComboBox currencyPairComboBox;
    private final PriceButton bidPriceButton, askPriceButton;
    private final SpreadLabel spreadLabel;
    private final LadderButton bidLadderButton, askLadderButton;
    private final QuantitySpinner quantitySpinner;
    private final CellConstraints cc = new CellConstraints();

    public PricePod() {
        this.currencyPairComboBox = new CurrencyPairComboBox();
        this.bidPriceButton = new PriceButton("0.72166");
        this.askPriceButton = new PriceButton("0.72185");
        this.spreadLabel = new SpreadLabel("1.9");
        this.bidLadderButton = new LadderButton("Bid");
        this.askLadderButton = new LadderButton("Ask");
        this.quantitySpinner = new QuantitySpinner();
        arrangeComponents();
    }

    private void arrangeComponents() {
        setLayout(new FormLayout("d", "d,d,d"));
        setSize(currencyPairComboBox, 50, 30);
        add(currencyPairComboBox, cc.xy(1, 1));
        add(createSecondRow(), cc.xy(1,2));
        add(createThirdRow(), cc.xy(1,3));
    }

    private JPanel createSecondRow() {
        JPanel pricePanel = new JPanel(new FormLayout("d,d", "d"));
        pricePanel.add(bidPriceButton, cc.xy(1, 1));
        pricePanel.add(askPriceButton, cc.xy(2, 1));
        setSize(bidPriceButton, 150, 75);
        setSize(askPriceButton, 150, 75);
        pricePanel.setAlignmentY(-0.1f);

        JPanel pricePanelWithSpread = new JPanel(){
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        pricePanelWithSpread.setLayout(new OverlayLayout(pricePanelWithSpread));

        pricePanelWithSpread.add(spreadLabel);
        pricePanelWithSpread.add(pricePanel);
        spreadLabel.setAlignmentX(0.5f);
        //spreadLabel.setAlignmentY(2f);
        setSize(spreadLabel, 25, 20);

        return pricePanelWithSpread;
    }
    private void setSize(Component component, int width, int height) {
        component.setPreferredSize(new Dimension(width, height));
        component.setMinimumSize(new Dimension(width, height));
        component.setMaximumSize(new Dimension(width, height));
    }

    private JPanel createThirdRow() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(bidLadderButton, BorderLayout.EAST);
        panel.add(askLadderButton, BorderLayout.WEST);
        panel.add(quantitySpinner, BorderLayout.CENTER);
        return panel;
    }
}
