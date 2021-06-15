package com.example.wcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointments {

    private long id;
    private long cabinId;
    private long patientId;
    private String fullname;
    private String cabinename;
    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;
    private int newOpointement;//if 1 it means this is new opontment to show to the paitent and making request 0
    private int toUpdate;//if 1 there is a request to update the date;
    private int history;//if 1 it means deleted and should be added to history if 0 the oposite
    private int request;//if 1 it's a request of an opointment if 0 the oposite

    public Appointments(long id, long cabinId, long patientId,String fullname,String cabinename,int hour, int minute, int day, int month, int year, int newOpointement, int toUpdate, int history, int request) {
        this.id = id;
        this.cabinId = cabinId;
        this.patientId = patientId;
        this.fullname = fullname;
        this.cabinename = cabinename;
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
        this.year = year;
        this.newOpointement = newOpointement;
        this.toUpdate = toUpdate;
        this.history = history;
        this.request = request;
    }
    public Appointments(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCabinId() {
        return cabinId;
    }

    public void setCabinId(long cabinId) {
        this.cabinId = cabinId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNewOpointement() {
        return newOpointement;
    }

    public void setNewOpointement(int newOpointement) {
        this.newOpointement = newOpointement;
    }

    public int getToUpdate() {
        return toUpdate;
    }

    public void setToUpdate(int toUpdate) {
        this.toUpdate = toUpdate;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Opointement{" +
                "id=" + id +
                ", cabinId=" + cabinId +
                ", patientId=" + patientId +
                ", fullname=" + fullname +
                ", cabinename=" + cabinename +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", newOpointement=" + newOpointement +
                ", toUpdate=" + toUpdate +
                ", history=" + history +
                ", request=" + request +
                '}';
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCabinename() {
        return cabinename;
    }

    public void setCabinename(String cabinename) {
        this.cabinename = cabinename;
    }
}

