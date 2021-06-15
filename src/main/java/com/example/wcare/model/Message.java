package com.example.wcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    private long id;
    private long cabineId;
    private long patientId;
    private int sender ;//if 0 cabine if 1 patient
    private String message;
    private String date;

    public Message(long id, long cabineId, long patientId, int sender, String message, String date) {
        this.id = id;
        this.cabineId = cabineId;
        this.patientId = patientId;
        this.sender = sender;
        this.message = message;
        this.date = date;
    }
    public Message(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCabineId() {
        return cabineId;
    }

    public void setCabineId(long cabineId) {
        this.cabineId = cabineId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", cabineId=" + cabineId +
                ", patientId=" + patientId +
                ", sender=" + sender +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
