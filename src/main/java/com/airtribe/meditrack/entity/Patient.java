package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * Patient entity extending Person.
 * Demonstrates: inheritance, deep copy semantics
 */
public class Patient extends Person {
    private static final long serialVersionUID = 1L;
    
    private String medicalHistory;
    private List<String> allergies;
    private String emergencyContact;

    public Patient(String id, String name, String email, String phone, int age,
                   String medicalHistory, String emergencyContact) 
            throws InvalidDataException {
        super(id, name, email, phone, age);
        this.medicalHistory = medicalHistory != null ? medicalHistory : "";
        this.allergies = new ArrayList<>();
        this.emergencyContact = Validator.validatePhone(emergencyContact);
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory != null ? medicalHistory : "";
    }

    public List<String> getAllergies() {
        return new ArrayList<>(allergies);
    }

    public void addAllergy(String allergy) {
        if (allergy != null && !allergy.isEmpty()) {
            allergies.add(allergy);
        }
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) throws InvalidDataException {
        this.emergencyContact = Validator.validatePhone(emergencyContact);
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.isEmpty()) return false;
        String lowerQuery = query.toLowerCase();
        return name.toLowerCase().contains(lowerQuery) ||
               email.toLowerCase().contains(lowerQuery) ||
               id.equals(query);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Patient cloned = (Patient) super.clone();
        cloned.allergies = new ArrayList<>(this.allergies);
        return cloned;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", allergies=" + allergies +
                '}';
    }
}