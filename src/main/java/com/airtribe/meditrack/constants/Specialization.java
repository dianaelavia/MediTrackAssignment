package com.airtribe.meditrack.constants;

public enum Specialization {
    CARDIOLOGY("Cardiology"),
    DERMATOLOGY("Dermatology"),
    ORTHOPEDICS("Orthopedics"),
    NEUROLOGY("Neurology"),
    PEDIATRICS("Pediatrics"),
    GENERAL("General Practice");

    private final String displayName;

    Specialization(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}