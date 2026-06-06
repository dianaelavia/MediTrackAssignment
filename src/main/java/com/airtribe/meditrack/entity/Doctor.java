package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.Validator;

/**
 * Doctor entity extending Person.
 * Demonstrates: inheritance, polymorphism, encapsulation
 */
public class Doctor extends Person {
    private static final long serialVersionUID = 1L;
    
    private Specialization specialization;
    private double consultationFee;
    private int yearsOfExperience;

    public Doctor(String id, String name, String email, String phone, int age,
                  Specialization specialization, double consultationFee, int yearsOfExperience) 
            throws InvalidDataException {
        super(id, name, email, phone, age);
        this.specialization = specialization;
        this.consultationFee = Validator.validateFee(consultationFee);
        this.yearsOfExperience = Validator.validateYearsOfExperience(yearsOfExperience);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) throws InvalidDataException {
        this.consultationFee = Validator.validateFee(consultationFee);
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) throws InvalidDataException {
        this.yearsOfExperience = Validator.validateYearsOfExperience(yearsOfExperience);
    }

    @Override
    public boolean matches(String query) {
        if (query == null || query.isEmpty()) return false;
        String lowerQuery = query.toLowerCase();
        return name.toLowerCase().contains(lowerQuery) ||
               specialization.getDisplayName().toLowerCase().contains(lowerQuery) ||
               id.equals(query);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", specialization=" + specialization.getDisplayName() +
                ", consultationFee=" + consultationFee +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}