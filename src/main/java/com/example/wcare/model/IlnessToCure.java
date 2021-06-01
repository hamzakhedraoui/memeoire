package com.example.wcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IlnessToCure {
    private long id;
    private long cabin_id;
    private String ilness;

    public IlnessToCure(long id, long cabin_id, String ilness) {
        this.id = id;
        this.cabin_id = cabin_id;
        this.ilness = ilness;
    }
    public IlnessToCure(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCabin_id() {
        return cabin_id;
    }

    public void setCabin_id(long cabin_id) {
        this.cabin_id = cabin_id;
    }

    public String getIlness() {
        return ilness;
    }

    public void setIlness(String ilness) {
        this.ilness = ilness;
    }

    @Override
    public String toString() {
        return "IlnessToCure{" +
                "id=" + id +
                ", cabin_id=" + cabin_id +
                ", ilness='" + ilness + '\'' +
                '}';
    }
}
