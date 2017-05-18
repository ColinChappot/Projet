package com.example.Colin.myapplication.backend.classes;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Colin on 11.05.2017.
 */

@Entity
public class MaterielNeeded {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public MaterielNeeded(Long id, int idTask, int idMaterial) {
        this.id = id;
        this.idTask = idTask;
        this.idMaterial = idMaterial;
    }

    public MaterielNeeded()
    {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    private int idTask;
    private int idMaterial;

}
