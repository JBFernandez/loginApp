package com.example.loginAPI.model;

public class Token {
    private final String accesToken;

    public Token(String accesToken) {
        this.accesToken = accesToken;
    } //constructor

    public String getAccesToken() {
        return accesToken;
    }//getter
}//class Token
