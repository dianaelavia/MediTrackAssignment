package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.interfaces.NotificationListener;
import java.util.*;

/**
 * Observer pattern implementation for appointment notifications.
 * Demonstrates: Observer pattern
 */
public class AppointmentNotificationService {
    private List<NotificationListener> listeners = new ArrayList<>();

    public void subscribe(NotificationListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(NotificationListener listener) {
        listeners.remove(listener);
    }

    public void notifyAppointmentScheduled(Appointment appointment) {
        for (NotificationListener listener : listeners) {
            listener.onAppointmentScheduled(appointment);
        }
    }

    public void notifyAppointmentCancelled(Appointment appointment) {
        for (NotificationListener listener : listeners) {
            listener.onAppointmentCancelled(appointment);
        }
    }
}