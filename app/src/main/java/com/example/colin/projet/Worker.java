package com.example.colin.projet;

import android.media.Image;

/**
 * Created by uadmin on 30.04.2017.
 */

public class Worker {

    private String login;
    private String password;
    private String  firstname;
    private String  lastname;
    private String phone;
    private Image image;


    public Worker ( String login, String password, String firstname, String lastname, String phone, Image image){
        this.login = login;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname;
        this.phone =phone;
        this.image = image;
    }
}
