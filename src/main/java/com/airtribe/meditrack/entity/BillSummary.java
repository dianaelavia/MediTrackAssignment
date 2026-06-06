package com.airtribe.meditrack.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Immutable BillSummary class.
 * Demonstrates: immutability, final fields, defensive copying
 */
public final class BillSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String billId;
    private final String patientName;
    private final String doctorName;
    private final double amount;
    private final double tax;
    private final double total;
    private final LocalDateTime billDate;
    private final boolean isPaid;

    private BillSummary(String billId, String patientName, String doctorName,
                       double amount, double tax, double total, LocalDateTime billDate, boolean isPaid) {
        this.billId = billId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.amount = amount;
        this.tax = tax;
        this.total = total;
        this.billDate = billDate;
        this.isPaid = isPaid;
    }

    public static BillSummary fromBill(Bill bill) {
        return new BillSummary(
            bill.getBillId(),
            bill.getAppointment().getPatient().getName(),
            bill.getAppointment().getDoctor().getName(),
            bill.getAmount(),
            bill.calculateTax(),
            bill.calculateTotal(),
            bill.getBillDate(),
            bill.isPaid()
        );
    }

    public String getBillId() {
        return billId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public double getAmount() {
        return amount;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public String toString() {
        return "BillSummary{" +
                "billId='" + billId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", total=" + total +
                ", isPaid=" + isPaid +
                '}';
    }
}