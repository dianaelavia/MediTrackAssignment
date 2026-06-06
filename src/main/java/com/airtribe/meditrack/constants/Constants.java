package com.airtribe.meditrack.constants;

public class Constants {
    public static final double TAX_RATE = 0.18; // 18% GST
    public static final double CONSULTATION_FEE = 500.0;
    public static final double EMERGENCY_FEE = 1500.0;
    
    public static final String PATIENTS_CSV_PATH = "data/patients.csv";
    public static final String DOCTORS_CSV_PATH = "data/doctors.csv";
    public static final String APPOINTMENTS_CSV_PATH = "data/appointments.csv";
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    private Constants() {
        // Utility class - private constructor
    }
}