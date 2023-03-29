package com.ambrosio.twitchdb.entita;

import com.ambrosio.twitchdb.gui.InsertionStreamerException;

public class Streamer {
    public Streamer(String nickname, String nome, int n_Seguaci){
        this.nickname = nickname;
        this.nome = nome;
        this.n_Seguaci = n_Seguaci;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNome() {
        return nome;
    }

    public int getN_Seguaci() {
        return n_Seguaci;
    }
    public boolean checkStreamer(){
        if(nickname.isEmpty() || nome.isEmpty() || n_Seguaci == 0) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " Nickname: "+this.nickname+"; "+
                " Nome: "+this.nome + "; "+
                " Numero di Seguaci: "+ this.n_Seguaci+";\n" +
                "----------------------------------------------------------------------------------------------------\n";
    }

    private String nickname;
    private String nome;
    private int n_Seguaci;
}
