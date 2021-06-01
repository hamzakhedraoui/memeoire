package com.example.wcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
    private long id;
    private long account_id;
    private long cabine_id;
    private String first_name;
    private String last_name;
    private String city;
    private String chronic_incess;

    public Patient(long id, long account_id, long cabine_id, String first_name, String last_name, String city, String chronic_incess) {
        this.id = id;
        this.account_id = account_id;
        this.cabine_id = cabine_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.chronic_incess = chronic_incess;
    }
    public Patient(){
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCabine_id() {
        return cabine_id;
    }

    public void setCabine_id(long cabine_id) {
        this.cabine_id = cabine_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChronic_incess() {
        return chronic_incess;
    }

    public void setChronic_incess(String chronic_incess) {
        this.chronic_incess = chronic_incess;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", cabine_id=" + cabine_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", city='" + city + '\'' +
                ", chronic_incess='" + chronic_incess + '\'' +
                '}';
    }
}
