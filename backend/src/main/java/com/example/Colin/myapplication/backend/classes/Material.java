package com.example.Colin.myapplication.backend.classes;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by uadmin on 04.05.2017.
 */

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*
     Declaration des variables
     */
    private String materialName;

    public Material(Long id, String materialName) {
        this.id = id;
        this.materialName = materialName;
    }

    public Material()
    {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }


}
