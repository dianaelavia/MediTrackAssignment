package com.airtribe.meditrack.util;

import java.util.*;

/**
 * Generic data store implementation.
 * Demonstrates: generics, reusability, type safety
 */
public class DataStore<T> {
    private List<T> items = new ArrayList<>();
    private Map<String, T> indexMap = new HashMap<>();

    public void add(T item, String key) {
        items.add(item);
        indexMap.put(key, item);
    }

    public void remove(String key) {
        T item = indexMap.remove(key);
        if (item != null) {
            items.remove(item);
        }
    }

    public T get(String key) {
        return indexMap.get(key);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public boolean exists(String key) {
        return indexMap.containsKey(key);
    }

    public int size() {
        return items.size();
    }

    public void clear() {
        items.clear();
        indexMap.clear();
    }
}