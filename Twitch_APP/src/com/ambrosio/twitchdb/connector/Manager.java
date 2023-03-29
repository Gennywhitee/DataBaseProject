package com.ambrosio.twitchdb.connector;

import com.ambrosio.twitchdb.entita.Streamer;
import com.ambrosio.twitchdb.gui.InsertionStreamerException;
import com.ambrosio.twitchdb.gui.MainGui;
import com.ambrosio.twitchdb.gui.TwitchFrame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    public Manager(){mainQuery = new MainQuery();}
    public List<Streamer> query0() throws SQLException {
        mainQuery.startConnection();
        ResultSet resultSet = mainQuery.getAll();
        List<Streamer> result = new ArrayList<>();
        while(resultSet.next()){
            String nickname = resultSet.getString(1);
            String nome = resultSet.getString(2);
            int n_Seguaci = resultSet.getInt(3);
            result.add(new Streamer(nickname,nome,n_Seguaci));
        }
        mainQuery.stopConnection();
        return result;
    }

    public void query1(Streamer s) throws SQLException, InsertionStreamerException {
        mainQuery.startConnection();
        if(s.checkStreamer() == false){
            throw new InsertionStreamerException();
        }else{
            mainQuery.insertStreamer(s);
        }
        mainQuery.stopConnection();
    }


    public void query2(Streamer streamers) throws SQLException, InsertionStreamerException {
        mainQuery.startConnection();
        mainQuery.cancella(streamers);
        mainQuery.stopConnection();
    }

    public void query3(Streamer s) throws SQLException, InsertionStreamerException {
        mainQuery.startConnection();
        if(this.findStreamer(s.getNickname()) != null && s.checkStreamer()){
            mainQuery.modifiedData(s);
        }else{
            throw new InsertionStreamerException();
        }
        mainQuery.stopConnection();
    }

    public List<Streamer> query4(int n,boolean flag) throws SQLException{
        mainQuery.startConnection();
        ResultSet resultSet = mainQuery.select(n,flag);
        List<Streamer> streamers = new ArrayList<>();
        while(resultSet.next()){
            String nickname = resultSet.getString(1);
            String nome = resultSet.getString(2);
            int n_Seguaci = resultSet.getInt(3);
            streamers.add(new Streamer(nickname,nome,n_Seguaci));
        }
        mainQuery.stopConnection();
        return streamers;
    }

    public Streamer findStreamer(String nickname) throws SQLException {
        mainQuery.startConnection();
        Streamer finded = null;
        List<Streamer> streamers = new ArrayList<>();
        ResultSet resultSet = mainQuery.getAll();
        while(resultSet.next()){
            String nick = resultSet.getNString(1);
            String nome = resultSet.getString(2);
            int n_Seguaci = resultSet.getInt(3);
            streamers.add(new Streamer(nick, nome, n_Seguaci));
        }
        for(Streamer tmp: streamers){
            if(tmp.getNickname().equals(nickname))
                finded = tmp;
        }
        return finded;
    }


    private static MainQuery mainQuery;
}

