package com.freeuni.oop.seminar12.core.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Consider using fluent interface or builder pattern.
 * This class is better be changed to be immutable:
 * Setter methods may be confusing for the user, it gives
 * impression that the underlying data is updated, while
 * in fact it isn't.
 */
public class Course {

    private int courseId;
    private String courseName;
    private int courseCredit;
    private String courseType;
    private List<Student> students;

    public int getCourseId() {
        return courseId;
    }

    // Should be part of the fluid interface / builder pattern
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    // Should be part of the fluid interface / builder pattern
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    // Should be part of the fluid interface / builder pattern
    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseType() {
        return courseType;
    }

    // Should be part of the fluid interface / builder pattern
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return students;
    }

    // Should be part of the fluid interface / builder pattern
    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
