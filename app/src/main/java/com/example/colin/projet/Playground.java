package com.example.colin.projet;

import android.media.Image;

/**
 * Created by uadmin on 26.04.2017. Je Précise que c est un code copié collé que je voulais adapter pour voir comment il fonctionnait
 */

public class Playground {

    private String town;
    private String name;
    private double surface ;
    // private ... timeTable;
    //private --- gps;
    private Image image;


    public Playground (String name){
        this.name = name;
    }


    public Playground (String town, String name, double surface, Image image){
        this.town=town;
        this.name = name;
        this.surface=surface;
        this.image = image;
    }

    public String getPlayGroundName(){
        return this.name;
    }
}
