package com.example.a2096129.alertfarm.entities;

import java.io.Serializable;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
public class User implements Serializable
{

    private int idUser;

    private String email;

    private String password;

    private String name;

    private String celular;

    public User()
    {
    }

    public User( String email, String password, String name,int idUser,String celular )
    {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.name = name;
        this.celular = celular;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


    @Override
    public String toString()
    {
        return "User{" + "id=" + idUser + ", email='" + email + '\'' + ", password='" + password + '\'' + ", name='"
            + name + ", celular='" + celular + '\'' + '}';
    }

}
