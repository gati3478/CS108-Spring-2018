package com.freeuni.oop.seminar12.core.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Consider using fluent interface or builder pattern.
 * This class is better be changed to be immutable:
 * Setter methods may be confusing for the user, it gives
 * impression that the underlying data is updated, while
 * in fact it isn't.
 */
public class Student {

    private int studentId;
    private String idNumber;
    private String firstName;
    private String lastName;
    private Date registerDate;
    private List<Course> courses;

    public int getStudentId() {
        return studentId;
    }

    // Should be part of the fluid interface / builder pattern
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getIdnumber() {
        return idNumber;
    }

    // Should be part of the fluid interface / builder pattern
    public void setIdnumber(String idnumber) {
        this.idNumber = idnumber;
    }

    public String getFirstName() {
        return firstName;
    }

    // Should be part of the fluid interface / builder pattern
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Should be part of the fluid interface / builder pattern
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    // Should be part of the fluid interface / builder pattern
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<Course> getCourses() {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        return courses;
    }

    // Should be part of the fluid interface / builder pattern
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
