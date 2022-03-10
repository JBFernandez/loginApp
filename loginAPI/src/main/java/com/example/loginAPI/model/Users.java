package com.example.loginAPI.model;

import com.example.loginAPI.utils.SHAUtil;

import javax.persistence.*; // *para traer todas las librerías

@Entity
@Table(name="users") //nombre de nuestra tabla, que estará relacionada (que creamos en MySQL)
public class Users { //POJO

    @Id //lo que ponemos debajo solo afecta a id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This annotation is used to specify the primary key generation strategy to use
    @Column(name = "id", unique = true, nullable = false)
    private Long id; //JPA no acepta datos primitivos, por eso Clase Wrapper
    private String name;
    private String email;
    private String password;

    public Users(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.setPassword(password); //utilizamos el método setPassword definido debajo para cuando agregamos un usuario
    } //Contructor

    public Users() { }//constructor vacío
    //Setters y Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    } // Getters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = SHAUtil.createHash(password); //Cuando registramos un usuario se encripta su password
    } // Setters
    public void setClearPassword (String password) {
        this.password = password;
    }//setClearPassword

}//Class Users
