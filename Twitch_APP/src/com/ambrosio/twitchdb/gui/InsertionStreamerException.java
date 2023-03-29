package com.ambrosio.twitchdb.gui;

import com.ambrosio.twitchdb.gui.InsertStreamerPanel;

public class InsertionStreamerException extends Exception{
    public InsertionStreamerException(){
        super();
    }
    public InsertionStreamerException(String msg){
        super(msg);
    }
}
