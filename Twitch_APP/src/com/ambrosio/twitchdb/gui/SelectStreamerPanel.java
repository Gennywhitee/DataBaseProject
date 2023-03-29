package com.ambrosio.twitchdb.gui;

import com.ambrosio.twitchdb.connector.MainQuery;
import com.ambrosio.twitchdb.entita.Streamer;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectStreamerPanel extends JPanel {
    public SelectStreamerPanel() throws SQLException {
        this.add(createMidPanel(), BorderLayout.CENTER);
    }
    private JPanel createMidPanel() throws SQLException {
        midPanel = new JPanel();
        comboBox = new JComboBox<>();
        List<Streamer> streamers = new ArrayList<>();
        streamers = MainGui.manager.query0();
        for(Streamer tmp : streamers) {
            comboBox.addItem(tmp.getNickname());
        }
        label = new JLabel("Scegli Streamer");
        midPanel.add(label);
        midPanel.add(comboBox);
        return midPanel;
    }

    protected JPanel midPanel;
    protected JLabel label;
    protected JComboBox<String> comboBox;
}
