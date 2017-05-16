package com.example.Colin.myapplication.backend.classes;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Colin on 11.05.2017.
 */

@Entity
public class InstallationPlaced {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int idPlayground ;
    private int idInstallation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdPlayground() {
        return idPlayground;
    }

    public void setIdPlayground(int idPlayground) {
        this.idPlayground = idPlayground;
    }

    public int getIdInstallation() {
        return idInstallation;
    }

    public void setIdInstallation(int idInstallation) {
        this.idInstallation = idInstallation;
    }
}
