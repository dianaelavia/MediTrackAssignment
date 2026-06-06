package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for Patient CRUD operations.
 */
public class PatientService {
    private DataStore<Patient> patientStore;
    private IdGenerator idGenerator;

    public PatientService() {
        this.patientStore = new DataStore<>();
        this.idGenerator = IdGenerator.getInstance();
    }

    public Patient addPatient(String name, String email, String phone, int age,
                             String medicalHistory, String emergencyContact) 
            throws InvalidDataException {
        String patientId = idGenerator.generatePatientId();
        Patient patient = new Patient(patientId, name, email, phone, age, medicalHistory, emergencyContact);
        patientStore.add(patient, patientId);
        return patient;
    }

    public Patient getPatientById(String patientId) {
        return patientStore.get(patientId);
    }

    public List<Patient> getAllPatients() {
        return patientStore.getAll();
    }

    public List<Patient> searchPatients(String query) {
        return patientStore.getAll().stream()
            .filter(patient -> patient.matches(query))
            .collect(Collectors.toList());
    }

    public List<Patient> searchPatientsByAge(int minAge, int maxAge) {
        return patientStore.getAll().stream()
            .filter(patient -> patient.getAge() >= minAge && patient.getAge() <= maxAge)
            .collect(Collectors.toList());
    }

    public boolean deletePatient(String patientId) {
        if (patientStore.exists(patientId)) {
            patientStore.remove(patientId);
            return true;
        }
        return false;
    }

    public Patient updatePatient(String patientId, Patient updatedPatient) 
            throws InvalidDataException {
        Patient patient = getPatientById(patientId);
        if (patient != null) {
            patient.setName(updatedPatient.getName());
            patient.setEmail(updatedPatient.getEmail());
            patient.setAge(updatedPatient.getAge());
            return patient;
        }
        return null;
    }
}