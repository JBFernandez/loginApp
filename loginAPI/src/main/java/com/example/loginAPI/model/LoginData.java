package com.example.loginAPI.model;

public class LoginData {
    private final String email; // como es final no puedo crear un constructor vacío, así que creamos un constructor igual pero igualamos a ""
    private final String password; //como es final no tendrá setters

    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }//constructor

    public LoginData() {
        this.email = "";
        this.password = "";
    }//constructor vacio

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    } //getters
}//class LoginData
