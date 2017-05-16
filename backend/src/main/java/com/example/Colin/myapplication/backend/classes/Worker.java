package com.example.Colin.myapplication.backend.classes;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Worker {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*
    Déclaration des variables
     */
    private String login;
    private String password;
    private String  firstname;
    private String  lastname;
    private String phone;
    private String idWorker;


    /*
    Constructeur
     */
    public Worker()
    {

    }

    public Worker(Long id, String login, String password, String firstname, String lastname, String phone, String idWorker) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.idWorker = idWorker;
    }

    /*
         Méthodes getName retourne nom prénom pour l'afficher dans la listView
        */
    public String getName(){
        return firstname + " " + lastname ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(String idWorker) {
        this.idWorker = idWorker;
    }
}
