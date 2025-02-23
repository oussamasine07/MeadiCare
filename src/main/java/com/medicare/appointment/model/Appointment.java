package com.medicare.appointment.model;

import com.medicare.patient.model.Patient;

public class Appointment {

    private int id;
    private int patientId;
    private int doctorId;
    private String appDate;
    private String appTime;
    private String motif;
    private Patient patient;

    public Appointment(int id, int patientId, int doctorId, String appDate, String appTime, String motif) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appDate = appDate;
        this.appTime = appTime;
        this.motif = motif;
    }

    public Appointment(int id, String appDate, String appTime, String motif, Patient patient) {
        this.id = id;
        this.appDate = appDate;
        this.appTime = appTime;
        this.motif = motif;
        this.patient = patient;
    }

    public Appointment(int patientId, int doctorId, String appDate, String appTime, String motif) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appDate = appDate;
        this.appTime = appTime;
        this.motif = motif;
    }

    public int getId () { return this.id; }

    public void setPatientId (int patientId ) {
        this.patientId = patientId;
    }
    public int getPatientId () { return this.patientId; }

    public void setDoctorId (int doctorId ) {
        this.doctorId = doctorId;
    }
    public int getDoctorId () { return this.doctorId; }

    public String getAppDate () { return this.appDate; }
    public void setAppDate ( String appDate ) {
        this.appDate = appDate;
    }

    public String getAppTime () { return this.appTime; }
    public void setAppTime ( String appTime ) {
        this.appTime = appTime;
    }

    public String getMotif () { return this.motif; }
    public void setMotif ( String motif ) {
        this.motif = motif;
    }

    public Patient getPatient() { return this.patient; }
}
