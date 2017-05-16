package com.example.Colin.myapplication.backend.classes;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by uadmin on 03.05.2017.
 */
@Entity
public class Installation {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    /*
    Declaration des variables
     */
    private String description;

    /*
    Constructeur
     */

    public Installation(long id, String description)
    {
        this.id = id;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Long getId() {
        return id;
    }


}
