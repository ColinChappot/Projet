package com.example.Colin.myapplication.backend.classes;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Colin on 11.05.2017.
 */

@Entity
public class State {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public State(Long id, String description) {
        this.id = id;
        Description = description;
    }

    public State()
    {

    }

    private String Description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

}
