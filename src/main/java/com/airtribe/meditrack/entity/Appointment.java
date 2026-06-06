package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Appointment entity.
 * Demonstrates: composition, immutability aspects, cloning
 */
public class Appointment implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentDateTime;
    private AppointmentStatus status;
    private String notes;

    public Appointment(String appointmentId, Patient patient, Doctor doctor,
                      LocalDateTime appointmentDateTime, String notes) 
            throws InvalidDataException {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDateTime = appointmentDateTime;
        this.status = AppointmentStatus.PENDING;
        this.notes = notes;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) throws AppointmentNotFoundException {
        if (status == null) {
            throw new AppointmentNotFoundException("Status cannot be null");
        }
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Appointment cloned = (Appointment) super.clone();
        cloned.patient = (Patient) patient.clone();
        cloned.doctor = (Doctor) doctor.clone();
        return cloned;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patient=" + patient.getName() +
                ", doctor=" + doctor.getName() +
                ", appointmentDateTime=" + appointmentDateTime +
                ", status=" + status.getDisplayName() +
                '}';
    }
}