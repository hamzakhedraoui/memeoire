package com.example.wcare.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MedicalRecord {
    private long id;
    private long cabineId;
    private long patientId;
    private String description;
    private String date;
    private int visible;

    public MedicalRecord(long id, long cabineId, long patientId, String description, String date,int visible) {
        this.id = id;
        this.cabineId = cabineId;
        this.patientId = patientId;
        this.description = description;
        this.date = date;
        this.visible = visible;
    }
    public MedicalRecord(){}
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", cabineId=" + cabineId +
                ", patientId=" + patientId +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", visible='" + visible + '\'' +
                '}';
    }
}
