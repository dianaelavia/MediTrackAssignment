package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for Doctor CRUD operations.
 * Demonstrates: CRUD operations, filtering, streams
 */
public class DoctorService {
    private DataStore<Doctor> doctorStore;
    private IdGenerator idGenerator;

    public DoctorService() {
        this.doctorStore = new DataStore<>();
        this.idGenerator = IdGenerator.getInstance();
    }

    public Doctor addDoctor(String name, String email, String phone, int age,
                           Specialization specialization, double fee, int yearsOfExperience) 
            throws InvalidDataException {
        String docId = idGenerator.generateDoctorId();
        Doctor doctor = new Doctor(docId, name, email, phone, age, specialization, fee, yearsOfExperience);
        doctorStore.add(doctor, docId);
        return doctor;
    }

    public Doctor getDoctorById(String doctorId) {
        return doctorStore.get(doctorId);
    }

    public List<Doctor> getAllDoctors() {
        return doctorStore.getAll();
    }

    public List<Doctor> searchDoctors(String query) {
        return doctorStore.getAll().stream()
            .filter(doctor -> doctor.matches(query))
            .collect(Collectors.toList());
    }

    public List<Doctor> getDoctorsBySpecialization(Specialization specialization) {
        return doctorStore.getAll().stream()
            .filter(doctor -> doctor.getSpecialization() == specialization)
            .collect(Collectors.toList());
    }

    public double getAverageConsultationFee() {
        return doctorStore.getAll().stream()
            .mapToDouble(Doctor::getConsultationFee)
            .average()
            .orElse(0.0);
    }

    public boolean deleteDoctor(String doctorId) {
        if (doctorStore.exists(doctorId)) {
            doctorStore.remove(doctorId);
            return true;
        }
        return false;
    }
}