package com.airtribe.meditrack.strategy;

import com.airtribe.meditrack.entity.Bill;

/**
 * Strategy interface for different billing approaches.
 * Demonstrates: Strategy pattern
 */
public interface BillingStrategy {
    double calculateBill(double baseAmount);
    String getStrategyName();
}