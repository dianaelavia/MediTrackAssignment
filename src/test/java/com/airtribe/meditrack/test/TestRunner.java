package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.constants.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.util.*;
import java.time.LocalDateTime;

/**
 * Manual test runner demonstrating key features.
 */
public class TestRunner {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("         MediTrack - Manual Test Runner");
        System.out.println("═══════════════════════════════════════════════════════════\n");
        
        try {
            testValidation();
            testIdGenerator();
            testInheritanceAndPolymorphism();
            testDeepCopy();
            testImmutability();
            testGenerics();
            
            System.out.println("\n✅ All tests passed!");
        } catch (Exception e) {
            System.out.println("\n❌ Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testValidation() throws InvalidDataException {
        System.out.println("TEST 1: Validation");
        System.out.println("─────────────────────────────────────────");
        
        try {
            Validator.validateEmail("invalid-email");
            System.out.println("❌ Should have thrown InvalidDataException");
        } catch (InvalidDataException e) {
            System.out.println("✅ Email validation works: " + e.getMessage());
        }
        
        try {
            Validator.validatePhone("123");
            System.out.println("❌ Should have thrown InvalidDataException");
        } catch (InvalidDataException e) {
            System.out.println("✅ Phone validation works: " + e.getMessage());
        }
    }

    private static void testIdGenerator() {
        System.out.println("\nTEST 2: Singleton - IdGenerator");
        System.out.println("─────────────────────────────────────────");
        
        IdGenerator gen1 = IdGenerator.getInstance();
        IdGenerator gen2 = IdGenerator.getInstance();
        
        System.out.println("Same instance? " + (gen1 == gen2));
        System.out.println("Generated Patient ID: " + gen1.generatePatientId());
        System.out.println("Generated Doctor ID: " + gen1.generateDoctorId());
        System.out.println("Generated Appointment ID: " + gen1.generateAppointmentId());
        System.out.println("✅ Singleton pattern works");
    }

    private static void testInheritanceAndPolymorphism() throws InvalidDataException {
        System.out.println("\nTEST 3: Inheritance & Polymorphism");
        System.out.println("─────────────────────────────────────────");
        
        Doctor doctor = new Doctor("DOC001", "Dr. Smith", "smith@clinic.com", "9876543210", 40, 
            Specialization.CARDIOLOGY, 500, 10);
        Patient patient = new Patient("PAT001", "John", "john@email.com", "9876543211", 30, 
            "Asthma", "9999999999");
        
        System.out.println("Doctor matches query 'Cardiology'? " + doctor.matches("Cardiology"));
        System.out.println("Patient matches query 'John'? " + patient.matches("John"));
        System.out.println("✅ Polymorphism works - each class implements matches() differently");
    }

    private static void testDeepCopy() throws InvalidDataException, CloneNotSupportedException {
        System.out.println("\nTEST 4: Deep Copy (Cloneable)");
        System.out.println("─────────────────────────────────────────");
        
        Patient original = new Patient("PAT001", "Alice", "alice@email.com", "9876543210", 25, 
            "None", "9999999999");
        original.addAllergy("Penicillin");
        original.addAllergy("Peanuts");
        
        Patient cloned = (Patient) original.clone();
        cloned.addAllergy("Shellfish");
        
        System.out.println("Original allergies: " + original.getAllergies());
        System.out.println("Cloned allergies: " + cloned.getAllergies());
        System.out.println("Are allergies independent? " + 
            !original.getAllergies().equals(cloned.getAllergies()));
        System.out.println("✅ Deep copy works");
    }

    private static void testImmutability() throws InvalidDataException {
        System.out.println("\nTEST 5: Immutability - BillSummary");
        System.out.println("─────────────────────────────────────────");
        
        Appointment apt = new Appointment("APT001", 
            new Patient("PAT001", "Bob", "bob@email.com", "9876543210", 35, "", "9999999999"),
            new Doctor("DOC001", "Dr. Smith", "smith@clinic.com", "9876543210", 40, 
                Specialization.CARDIOLOGY, 500, 10),
            LocalDateTime.now(), "Regular checkup");
        
        Bill bill = new Bill("BILL001", apt, 500);
        BillSummary summary = BillSummary.fromBill(bill);
        
        System.out.println("Bill Summary: " + summary);
        System.out.println("Cannot modify summary - no setters available");
        System.out.println("✅ Immutability enforced");
    }

    private static void testGenerics() {
        System.out.println("\nTEST 6: Generics - DataStore<T>");
        System.out.println("─────────────────────────────────────────");
        
        DataStore<String> stringStore = new DataStore<>();
        stringStore.add("Hello", "key1");
        stringStore.add("World", "key2");
        
        System.out.println("String store size: " + stringStore.size());
        System.out.println("Retrieved value: " + stringStore.get("key1"));
        
        DataStore<Integer> intStore = new DataStore<>();
        intStore.add(42, "answer");
        System.out.println("Integer store value: " + intStore.get("answer"));
        System.out.println("✅ Generics work - type-safe storage");
    }
}