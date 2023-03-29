package com.ambrosio.twitchdb.gui;

import com.ambrosio.twitchdb.connector.MainQuery;
import com.ambrosio.twitchdb.connector.Manager;

import javax.swing.*;
import java.sql.SQLException;

public class MainGui {
    public static void main(String[] args) throws SQLException, InsertionStreamerException {
        JFrame frame = new TwitchFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    static protected Manager manager = new Manager();
}
