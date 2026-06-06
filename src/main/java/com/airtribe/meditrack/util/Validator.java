package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;
import java.util.regex.Pattern;

/**
 * Centralized validation utility.
 * Demonstrates: static methods, reusability
 */
public class Validator {
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[0-9]{10}$");

    private Validator() {
    }

    public static String validateId(String id) throws InvalidDataException {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidDataException("ID cannot be null or empty");
        }
        return id.trim();
    }

    public static String validateName(String name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Name cannot be null or empty");
        }
        if (name.trim().length() < 2) {
            throw new InvalidDataException("Name must be at least 2 characters");
        }
        return name.trim();
    }

    public static String validateEmail(String email) throws InvalidDataException {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidDataException("Invalid email format");
        }
        return email.trim();
    }

    public static String validatePhone(String phone) throws InvalidDataException {
        if (phone == null || !PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidDataException("Phone must be 10 digits");
        }
        return phone;
    }

    public static int validateAge(int age) throws InvalidDataException {
        if (age < 1 || age > 150) {
            throw new InvalidDataException("Age must be between 1 and 150");
        }
        return age;
    }

    public static double validateFee(double fee) throws InvalidDataException {
        if (fee < 0) {
            throw new InvalidDataException("Fee cannot be negative");
        }
        return fee;
    }

    public static int validateYearsOfExperience(int years) throws InvalidDataException {
        if (years < 0 || years > 70) {
            throw new InvalidDataException("Years of experience must be between 0 and 70");
        }
        return years;
    }
}