package com.florentina.pethotel.exception.domain;

public class NoRoomsAvailableException extends Exception{
    public NoRoomsAvailableException(String message){
        super(message);
    }
}