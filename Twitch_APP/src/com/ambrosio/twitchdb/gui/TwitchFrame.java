package com.ambrosio.twitchdb.gui;

import com.ambrosio.twitchdb.connector.MainQuery;
import com.ambrosio.twitchdb.entita.Streamer;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TwitchFrame extends JFrame {
    public TwitchFrame() throws InsertionStreamerException {
        this.setSize(520,500);
        this.add(this.createTopJPanel(),BorderLayout.NORTH);
        this.add(this.createMidPanel(),BorderLayout.CENTER);
    }

    public JPanel createTopJPanel() throws InsertionStreamerException{
        topPanel = new JPanel();
        button = new JButton("Esegui");

        comboBox = new JComboBox();
        comboBox.addItem("Stampa");
        comboBox.addItem("Inserisci");
        comboBox.addItem("Cancellazione");
        comboBox.addItem("Modifica");
        comboBox.addItem("Selezione");
        button.addActionListener(listener ->{
            switch(comboBox.getSelectedIndex()) {
                case 0: {
                    try {
                        textArea.setText("");
                        list = MainGui.manager.query0();
                        list.forEach(streamer -> {
                            textArea.append(streamer.toString());
                        });
                        MainGui.manager.query0();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 1: {
                    InsertStreamerPanel insertStreamerPanel = new InsertStreamerPanel();
                    int result = JOptionPane.showConfirmDialog(TwitchFrame.this, insertStreamerPanel, "Inserisci nuovo Streamer", JOptionPane.OK_CANCEL_OPTION);

                    if (result == 0) {
                        try {
                            String nickname = insertStreamerPanel.nickField.getText();
                            String nome = insertStreamerPanel.nomeField.getText();
                            int n_seguaci = Integer.parseInt(insertStreamerPanel.seguaciField.getText());
                            Streamer streamer = new Streamer(nickname, nome, n_seguaci);
                            if (!streamer.checkStreamer()) {
                                JOptionPane.showMessageDialog(this, JOptionPane.ERROR_MESSAGE, "Errore", JOptionPane.NO_OPTION);
                            } else {
                                if (MainGui.manager.findStreamer(streamer.getNickname()) != null) {
                                    JOptionPane.showMessageDialog(this, JOptionPane.ERROR_MESSAGE, "Errore Streamer già esistente", JOptionPane.NO_OPTION);
                                }
                                MainGui.manager.query1(streamer);
                            }
                        } catch (InsertionStreamerException e) {
                            JOptionPane.showMessageDialog(this, "parametri invalidi", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
                case 2: {
                    try {
                        SelectStreamerPanel selectStreamerPanel = new SelectStreamerPanel();
                        int result = JOptionPane.showConfirmDialog(TwitchFrame.this, selectStreamerPanel, "Scegliere lo streamer", JOptionPane.OK_CANCEL_OPTION);

                        if (result == 0) {
                            String nick = (String) selectStreamerPanel.comboBox.getSelectedItem();
                            Streamer s = MainGui.manager.findStreamer(nick);
                            MainGui.manager.query2(s);
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Errore durante l'inserimento", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (InsertionStreamerException e) {
                        JOptionPane.showMessageDialog(this, "Errore", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 3: {
                    try{
                        SelectStreamerPanel selectStreamerPanel = new SelectStreamerPanel();
                        int result = JOptionPane.showConfirmDialog(TwitchFrame.this, selectStreamerPanel, "Scegliere lo streamer", JOptionPane.OK_CANCEL_OPTION);

                        if(result == 0){
                            String nick = (String) selectStreamerPanel.comboBox.getSelectedItem();
                            Streamer s = MainGui.manager.findStreamer(nick);
                            ModifyStreamerPanel modifyStreamerPanel = new ModifyStreamerPanel(s);
                            JOptionPane.showConfirmDialog(this,modifyStreamerPanel,"Streamer da Modificare",JOptionPane.YES_OPTION);
                            Streamer tmp = new Streamer(s.getNickname(),modifyStreamerPanel.nomeField.getText(),Integer.parseInt(modifyStreamerPanel.n_SeguaciField.getText()));
                            MainGui.manager.query3(tmp);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (InsertionStreamerException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 4:{
                    SelectionPanel selectionPanel = new SelectionPanel();
                    int result = JOptionPane.showConfirmDialog(this,selectionPanel,"Scegli numero di streamer min/max",JOptionPane.YES_OPTION);
                    if(result == 0){
                        int nSeguaci = Integer.parseInt(selectionPanel.seguaciSelect.getText());
                        boolean flag = selectionPanel.radioButtonMaggiore.isSelected();
                            try {
                                List<Streamer> streamers = new ArrayList<>();
                                streamers = MainGui.manager.query4(nSeguaci,flag);
                                textArea.setText("");
                                streamers.forEach(e -> {
                                    textArea.append(e.toString());
                                });
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                    }
                    break;
                }
            }
        });
        topPanel.add(button,BorderLayout.WEST);
        topPanel.add(comboBox,BorderLayout.CENTER);
        return topPanel;
    }

    public JPanel createMidPanel(){
        midPanel = new JPanel();
        textArea = new JTextArea(27,50);
        textArea.setEditable(false);

        scrollPane = new JScrollPane(textArea);
        //scrollPane.setSize;
        midPanel.add(textArea);
        midPanel.add(scrollPane);
        return midPanel;
    }
    protected JPanel topPanel;
    protected JComboBox comboBox;
    protected JTextArea textArea;
    protected JButton button;
    private String method;
    protected JPanel midPanel;
    protected JScrollPane scrollPane;
    protected List<Streamer> list = new ArrayList<>();
}
