package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.constants.Constants;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Bill entity implementing Payable interface.
 * Strategy pattern applied via BillFactory.
 */
public class Bill implements Payable, Serializable {
    private static final long serialVersionUID = 1L;
    
    private String billId;
    private Appointment appointment;
    private double amount;
    private LocalDateTime billDate;
    private boolean isPaid;

    public Bill(String billId, Appointment appointment, double amount) {
        this.billId = billId;
        this.appointment = appointment;
        this.amount = amount;
        this.billDate = LocalDateTime.now();
        this.isPaid = false;
    }

    public String getBillId() {
        return billId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    @Override
    public double calculateTotal() {
        return amount + calculateTax();
    }

    @Override
    public double calculateTax() {
        return amount * Constants.TAX_RATE;
    }

    @Override
    public String generateBillDescription() {
        return String.format(
            "Bill ID: %s\nDoctor: %s\nAmount: %.2f\nTax: %.2f\nTotal: %.2f\nStatus: %s",
            billId, appointment.getDoctor().getName(), amount, calculateTax(),
            calculateTotal(), isPaid ? "PAID" : "PENDING"
        );
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", amount=" + amount +
                ", billDate=" + billDate +
                ", isPaid=" + isPaid +
                '}';
    }
}