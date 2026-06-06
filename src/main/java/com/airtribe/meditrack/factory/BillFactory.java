package com.airtribe.meditrack.factory;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.constants.Constants;

/**
 * Factory for creating different types of bills.
 * Demonstrates: Factory pattern
 */
public class BillFactory {
    
    private BillFactory() {
    }

    public static Bill createConsultationBill(String billId, Appointment appointment) {
        return new Bill(billId, appointment, Constants.CONSULTATION_FEE);
    }

    public static Bill createEmergencyBill(String billId, Appointment appointment) {
        return new Bill(billId, appointment, Constants.EMERGENCY_FEE);
    }

    public static Bill createCustomBill(String billId, Appointment appointment, double amount) {
        return new Bill(billId, appointment, amount);
    }
}