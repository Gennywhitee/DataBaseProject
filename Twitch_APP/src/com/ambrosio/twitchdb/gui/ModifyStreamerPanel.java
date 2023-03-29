package com.ambrosio.twitchdb.gui;

import com.ambrosio.twitchdb.entita.Streamer;

import javax.swing.*;
import java.awt.*;

public class ModifyStreamerPanel extends JPanel {
    public ModifyStreamerPanel(Streamer s){
        this.add(createMidPanel(s), BorderLayout.CENTER);

    }
    private JPanel createMidPanel(Streamer s){
        midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(3,2));
        nickLabel = new JLabel("Inserisci nickname: ");
        nickField = new JTextField(8);
        nomeLabel = new JLabel("Inserisci nome: ");
        nomeField = new JTextField(8);
        n_SeguaciLabel = new JLabel("Inserisci numero di seguaci: ");
        n_SeguaciField = new JTextField(8);
        nickField.setText(s.getNickname());
        nickField.setEditable(false);
        nomeField.setText(s.getNome());
        n_SeguaciField.setText(s.getN_Seguaci()+"");
        midPanel.add(nickLabel);
        midPanel.add(nickField);
        midPanel.add(nomeLabel);
        midPanel.add(nomeField);
        midPanel.add(n_SeguaciLabel);
        midPanel.add(n_SeguaciField);
        return midPanel;
    }
    protected JPanel midPanel;
    protected JTextField nickField;
    protected JTextField nomeField;
    protected JTextField n_SeguaciField;
    protected JLabel nickLabel;
    protected JLabel nomeLabel;
    protected JLabel n_SeguaciLabel;
}
