package com.airtribe.meditrack.strategy;

/**
 * Concrete strategy for emergency consultation billing with surcharge.
 */
public class EmergencyBillingStrategy implements BillingStrategy {
    private static final double EMERGENCY_SURCHARGE = 0.5;

    @Override
    public double calculateBill(double baseAmount) {
        double withSurcharge = baseAmount * (1 + EMERGENCY_SURCHARGE);
        return withSurcharge + (withSurcharge * 0.18);
    }

    @Override
    public String getStrategyName() {
        return "Emergency Consultation";
    }
}