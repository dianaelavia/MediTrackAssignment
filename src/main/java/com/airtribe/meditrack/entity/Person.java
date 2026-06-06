package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;
import com.airtribe.meditrack.interfaces.Searchable;
import java.io.Serializable;

/**
 * Abstract base class for all persons in the system.
 * Demonstrates: encapsulation, inheritance, abstraction
 */
public abstract class Person implements Searchable, Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
    protected String id;
    protected String name;
    protected String email;
    protected String phone;
    protected int age;

    public Person(String id, String name, String email, String phone, int age) 
            throws com.airtribe.meditrack.exception.InvalidDataException {
        this.id = Validator.validateId(id);
        this.name = Validator.validateName(name);
        this.email = Validator.validateEmail(email);
        this.phone = Validator.validatePhone(phone);
        this.age = Validator.validateAge(age);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws com.airtribe.meditrack.exception.InvalidDataException {
        this.name = Validator.validateName(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws com.airtribe.meditrack.exception.InvalidDataException {
        this.email = Validator.validateEmail(email);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws com.airtribe.meditrack.exception.InvalidDataException {
        this.phone = Validator.validatePhone(phone);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws com.airtribe.meditrack.exception.InvalidDataException {
        this.age = Validator.validateAge(age);
    }

    @Override
    public String getSearchableId() {
        return id;
    }

    @Override
    public abstract boolean matches(String query);

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}