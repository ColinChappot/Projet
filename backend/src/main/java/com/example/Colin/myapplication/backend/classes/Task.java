package com.example.Colin.myapplication.backend.classes;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by uadmin on 01.05.2017.
 */
@Entity
public class Task {
    /*
    Declaration variables
     */

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private String description;
    private int idPlayground;
    private int idWorker;
    private String observation;
    private String nom;
    private int idState;
    private String date;

    /*
    Constructor
     */
    public Task(String description){
        this.description = description;
    }

    public Task()
    {}

    public Task(Long id, String description, int idPlayground, int idWorker, String observation, String nom, int idState, String date) {
        this.id = id;
        this.description = description;
        this.idPlayground = idPlayground;
        this.idWorker = idWorker;
        this.observation = observation;
        this.nom = nom;
        this.idState = idState;
        this.date = date;
    }

    public Long getId() {
        return id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastTask(){
        return this.description + " - " + this.date;
    }

    public int getIdPlayground() {
        return idPlayground;
    }

    public void setIdPlayground(int idPlayground) {
        this.idPlayground = idPlayground;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }
}
