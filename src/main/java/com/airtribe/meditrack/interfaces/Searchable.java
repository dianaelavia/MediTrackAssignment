package com.airtribe.meditrack.interfaces;

public interface Searchable {
    boolean matches(String query);
    String getSearchableId();
}