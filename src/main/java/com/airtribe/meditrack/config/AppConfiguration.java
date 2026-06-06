package com.airtribe.meditrack.config;

/**
 * Singleton configuration holder.
 * Demonstrates: Singleton pattern (lazy initialization)
 */
public class AppConfiguration {
    private static AppConfiguration instance;
    private boolean dataLoaded;
    private String appVersion = "1.0.0";

    private AppConfiguration() {
        this.dataLoaded = false;
    }

    public static synchronized AppConfiguration getInstance() {
        if (instance == null) {
            instance = new AppConfiguration();
        }
        return instance;
    }

    public boolean isDataLoaded() {
        return dataLoaded;
    }

    public void setDataLoaded(boolean loaded) {
        this.dataLoaded = loaded;
    }

    public String getAppVersion() {
        return appVersion;
    }
}