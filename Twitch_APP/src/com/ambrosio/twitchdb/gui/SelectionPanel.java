package com.ambrosio.twitchdb.gui;

import javax.swing.*;
import java.awt.*;

public class SelectionPanel extends JPanel {
    public SelectionPanel(){
        this.add(createMidPanel(),BorderLayout.CENTER);
    }
    private JPanel createMidPanel(){
        midPanel = new JPanel();
        radioButtonMenuItem = new ButtonGroup();
        seguaciSelect = new JTextField(10);
        radioButtonMaggiore = new JRadioButton("Maggiore");
        radioButtonMinore = new JRadioButton("Minore");
        label = new JLabel("Inserisci numero seguaci: ");
        radioButtonMenuItem.add(radioButtonMinore);
        radioButtonMenuItem.add(radioButtonMaggiore);
        midPanel.setLayout(new GridLayout(2,2));
        midPanel.add(radioButtonMinore);
        midPanel.add(radioButtonMaggiore);
        midPanel.add(label);
        midPanel.add(seguaciSelect);
        return midPanel;
    }
    protected JPanel midPanel;
    protected JTextField seguaciSelect;
    protected JRadioButton radioButtonMaggiore;
    protected JRadioButton radioButtonMinore;
    protected ButtonGroup radioButtonMenuItem;
    protected JLabel label;

}
