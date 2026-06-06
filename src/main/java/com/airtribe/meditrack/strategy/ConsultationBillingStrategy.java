package com.airtribe.meditrack.strategy;

import com.airtribe.meditrack.constants.Constants;

/**
 * Concrete strategy for standard consultation billing.
 */
public class ConsultationBillingStrategy implements BillingStrategy {
    @Override
    public double calculateBill(double baseAmount) {
        return baseAmount + (baseAmount * Constants.TAX_RATE);
    }

    @Override
    public String getStrategyName() {
        return "Standard Consultation";
    }
}