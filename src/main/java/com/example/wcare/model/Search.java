package com.example.wcare.model;

public class Search {
    private String city;
    private String speciality;

    public Search(String city, String speciality) {
        this.city = city;
        this.speciality = speciality;
    }
    public Search(){}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Search{" +
                "city='" + city + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
