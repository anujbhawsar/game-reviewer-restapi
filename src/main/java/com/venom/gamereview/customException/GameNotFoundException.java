package com.venom.gamereview.customException;

public class GameNotFoundException extends Exception{
    public GameNotFoundException(String message){
        super(message);
    }
}
