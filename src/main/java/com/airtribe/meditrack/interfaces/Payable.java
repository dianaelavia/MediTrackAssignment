package com.airtribe.meditrack.interfaces;

public interface Payable {
    double calculateTotal();
    double calculateTax();
    String generateBillDescription();
}