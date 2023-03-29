package com.ambrosio.twitchdb.gui;

import javax.swing.*;
import java.awt.*;

public class InsertStreamerPanel extends JPanel {
    public InsertStreamerPanel(){
        this.add(createMidPanel(),BorderLayout.CENTER);
        this.add(crateBotPanel(),BorderLayout.SOUTH);
    }

    private JPanel createMidPanel(){
        midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(3,2));
        nickLabel = new JLabel("Inserisci nickname: ");
        nickField = new JTextField(8);
        nomeLabel = new JLabel("Inserisci nome: ");
        nomeField = new JTextField(8);
        seguaciLabel = new JLabel("Inserisci numero di seguaci: ");
        seguaciField = new JTextField(8);
        midPanel.add(nickLabel);
        midPanel.add(nickField);
        midPanel.add(nomeLabel);
        midPanel.add(nomeField);
        midPanel.add(seguaciLabel);
        midPanel.add(seguaciField);
        return midPanel;
    }
    private JPanel crateBotPanel(){
        botPanel = new JPanel();
        return botPanel;
    }

    protected JPanel midPanel;
    protected JPanel botPanel;
    protected JTextField nickField;
    protected JTextField nomeField;
    protected JTextField seguaciField;
    protected JLabel nickLabel;
    protected JLabel nomeLabel;
    protected JLabel seguaciLabel;


}
