package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Singleton IdGenerator using eager initialization.
 * Demonstrates: Singleton pattern, thread-safe counter
 */
public class IdGenerator {
    private static final IdGenerator INSTANCE = new IdGenerator();
    
    private final AtomicInteger patientCounter = new AtomicInteger(1000);
    private final AtomicInteger doctorCounter = new AtomicInteger(2000);
    private final AtomicInteger appointmentCounter = new AtomicInteger(5000);
    private final AtomicInteger billCounter = new AtomicInteger(8000);

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String generatePatientId() {
        return "PAT" + patientCounter.incrementAndGet();
    }

    public String generateDoctorId() {
        return "DOC" + doctorCounter.incrementAndGet();
    }

    public String generateAppointmentId() {
        return "APT" + appointmentCounter.incrementAndGet();
    }

    public String generateBillId() {
        return "BILL" + billCounter.incrementAndGet();
    }
}