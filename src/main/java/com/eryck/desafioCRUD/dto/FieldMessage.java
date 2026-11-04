package com.eryck.desafioCRUD.dto;

public class FieldMessage {

    private String name;
    private String message;

    public FieldMessage(String message, String name) {
        this.message = message;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
