package com.example.wcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cabine {
    private long id;
    private long account_id;
    private String name;
    private String phone;
    private String city;
    private String address;
    private String about_us;

    public Cabine(long id, long account_id, String name, String phone, String city, String address, String about_us) {
        this.id = id;
        this.account_id = account_id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.about_us = about_us;
    }
    public Cabine(){
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Cabine{" +
                "id=" + id +
                ", account_id='" + account_id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", aboutus='" + about_us + '\'' +
                '}';
    }

    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }
}
