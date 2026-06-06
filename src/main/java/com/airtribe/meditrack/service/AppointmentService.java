package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.constants.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.observer.AppointmentNotificationService;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for Appointment management.
 * Demonstrates: CRUD operations, Observer pattern integration
 */
public class AppointmentService {
    private DataStore<Appointment> appointmentStore;
    private IdGenerator idGenerator;
    private AppointmentNotificationService notificationService;

    public AppointmentService(AppointmentNotificationService notificationService) {
        this.appointmentStore = new DataStore<>();
        this.idGenerator = IdGenerator.getInstance();
        this.notificationService = notificationService;
    }

    public Appointment scheduleAppointment(Patient patient, Doctor doctor,
                                          LocalDateTime dateTime, String notes) 
            throws InvalidDataException {
        String appointmentId = idGenerator.generateAppointmentId();
        Appointment appointment = new Appointment(appointmentId, patient, doctor, dateTime, notes);
        appointmentStore.add(appointment, appointmentId);
        notificationService.notifyAppointmentScheduled(appointment);
        return appointment;
    }

    public Appointment getAppointmentById(String appointmentId) 
            throws AppointmentNotFoundException {
        Appointment appointment = appointmentStore.get(appointmentId);
        if (appointment == null) {
            throw new AppointmentNotFoundException("Appointment not found: " + appointmentId);
        }
        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentStore.getAll();
    }

    public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {
        return appointmentStore.getAll().stream()
            .filter(apt -> apt.getStatus() == status)
            .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        return appointmentStore.getAll().stream()
            .filter(apt -> apt.getDoctor().getId().equals(doctor.getId()))
            .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        return appointmentStore.getAll().stream()
            .filter(apt -> apt.getPatient().getId().equals(patient.getId()))
            .collect(Collectors.toList());
    }

    public void cancelAppointment(String appointmentId) 
            throws AppointmentNotFoundException {
        Appointment appointment = getAppointmentById(appointmentId);
        appointment.setStatus(AppointmentStatus.CANCELLED);
        notificationService.notifyAppointmentCancelled(appointment);
    }

    public void confirmAppointment(String appointmentId) 
            throws AppointmentNotFoundException {
        Appointment appointment = getAppointmentById(appointmentId);
        appointment.setStatus(AppointmentStatus.CONFIRMED);
    }

    public Map<String, Long> getAppointmentsPerDoctor() {
        return appointmentStore.getAll().stream()
            .collect(Collectors.groupingBy(
                apt -> apt.getDoctor().getName(),
                Collectors.counting()
            ));
    }
}