package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.interfaces.NotificationListener;

/**
 * Concrete observer - console notifications.
 */
public class ConsoleNotificationListener implements NotificationListener {
    @Override
    public void onAppointmentScheduled(Appointment appointment) {
        System.out.println("📅 [NOTIFICATION] Appointment Scheduled:");
        System.out.println("   Patient: " + appointment.getPatient().getName());
        System.out.println("   Doctor: " + appointment.getDoctor().getName());
        System.out.println("   Date: " + appointment.getAppointmentDateTime());
    }

    @Override
    public void onAppointmentCancelled(Appointment appointment) {
        System.out.println("❌ [NOTIFICATION] Appointment Cancelled:");
        System.out.println("   Patient: " + appointment.getPatient().getName());
        System.out.println("   Doctor: " + appointment.getDoctor().getName());
    }
}