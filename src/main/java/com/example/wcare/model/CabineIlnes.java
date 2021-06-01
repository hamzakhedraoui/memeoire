package com.example.wcare.model;

import java.util.ArrayList;

public class CabineIlnes {
    private long id;
    private long account_id;
    private String name;
    private String phone;
    private String city;
    private String address;
    private String about_us;
    private ArrayList<IlnessToCure> ilness = new ArrayList<>();

    public CabineIlnes(long id, long account_id, String name, String phone, String city, String address, String about_us, ArrayList<IlnessToCure> ilness) {
        this.id = id;
        this.account_id = account_id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.about_us = about_us;
        this.ilness = ilness;
    }
    public CabineIlnes(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
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

    public String getAbout_us() {
        return about_us;
    }

    public void setAbout_us(String about_us) {
        this.about_us = about_us;
    }

    public ArrayList<IlnessToCure> getIlness() {
        return ilness;
    }

    public void setIlness(ArrayList<IlnessToCure> ilness) {
        this.ilness = ilness;
    }

    public void addIlness(IlnessToCure ilnessToCure){
        this.ilness.add(ilnessToCure);
    }

    @Override
    public String toString() {
        return "CabineIlnes{" +
                "id=" + id +
                ", account_id=" + account_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", about_us='" + about_us + '\'' +
                ", ilness=" + ilness +
                '}';
    }
}
