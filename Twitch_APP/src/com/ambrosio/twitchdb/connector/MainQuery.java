package com.ambrosio.twitchdb.connector;

import com.ambrosio.twitchdb.entita.Streamer;

import java.sql.*;

public class MainQuery {
    public void startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, passwd);
            stm = con.createStatement();
        } catch (Exception e) {
            System.out.println("Connessione fallita");
        }
    }
    public void stopConnection() throws SQLException {
        if(con != null)
            con.close();
    }
    public void inserimento(){

    }
    public ResultSet getAll() throws SQLException {
        ResultSet resultSet = null;
        resultSet = stm.executeQuery("SELECT * FROM STREAMER");
        return resultSet;
    }
    public void insertStreamer(Streamer s) throws SQLException{
        String query = "insert into Streamer values" + " (" +
                '"'+ s.getNickname() + '"'+','+'"'+ s.getNome() + '"'+','+ s.getN_Seguaci()+")"+';';
        stm.execute(query);
    }

    public void modifiedData(Streamer s) throws SQLException {
        String query = "update Streamer SET"+"\n"+
                "nickname = "+'"'+s.getNickname()+'"'+','+
                "nome = "+'"'+s.getNome()+'"'+','+
                "n_Seguaci = "+'"'+s.getN_Seguaci()+'"'+"\n"+
                "Where Streamer.nickname = "+'"'+s.getNickname()+'"'+';';
        stm.execute(query);
    }
    public void cancella(Streamer s) throws SQLException {
        String query = "delete from Streamer where Streamer.nickname = " +'"'+s.getNickname()+'"'+';';
        stm.execute(query);
    }

    public ResultSet select(int n,boolean flag) throws SQLException {
        ResultSet resultSet = null;
        String query;
        if(flag) {
            query = "Select *\n from Streamer\n where Streamer.n_Seguaci >"+n+';';
        }
        else{
            query = "Select *\n from Streamer\n where Streamer.n_Seguaci <"+n+';';
        }
        resultSet = stm.executeQuery(query);
        return resultSet;
    }



    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/twitchdb";
    static String username = "root";
    static String passwd = "password123";
    static Statement stm = null;

}

