package com.airtribe.meditrack.util;

import java.io.*;
import java.util.*;

/**
 * CSV utility for reading and writing.
 * Demonstrates: File I/O, try-with-resources
 */
public class CSVUtil {
    private static final String DELIMITER = ",";

    private CSVUtil() {
    }

    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                data.add(values);
            }
        }
        return data;
    }

    public static void writeCSV(String filePath, List<String[]> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                writer.write(String.join(DELIMITER, row));
                writer.newLine();
            }
        }
    }

    public static void appendCSV(String filePath, String[] row) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(String.join(DELIMITER, row));
            writer.newLine();
        }
    }
}