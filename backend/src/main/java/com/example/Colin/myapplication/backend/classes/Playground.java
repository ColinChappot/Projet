package com.example.Colin.myapplication.backend.classes;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Playground {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /*
   Declaration des variables
    */
    private String town;
    private String name;
    private double surface ;

    public String getTimeTableToAvoid() {
        return timeTableToAvoid;
    }

    public void setTimeTableToAvoid(String timeTableToAvoid) {
        this.timeTableToAvoid = timeTableToAvoid;
    }

    public String getGPSLocalisation() {
        return GPSLocalisation;
    }

    public void setGPSLocalisation(String GPSLocalisation) {
        this.GPSLocalisation = GPSLocalisation;
    }

    private String idPlayground;
    private String timeTableToAvoid;
    private String GPSLocalisation;



    /*
    Constructeur
     */
    public Playground()
    {

    }

    public Playground(Long id, String town, String name, double surface, String idPlayground, String timeTableToAvoid, String GPSLocalisation) {
        this.id = id;
        this.town = town;
        this.name = name;
        this.surface = surface;
        this.idPlayground = idPlayground;
        this.timeTableToAvoid = timeTableToAvoid;
        this.GPSLocalisation = GPSLocalisation;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public String getIdPlayground() {
        return idPlayground;
    }

    public void setIdPlayground(String idPlayground) {
        this.idPlayground = idPlayground;
    }
}
