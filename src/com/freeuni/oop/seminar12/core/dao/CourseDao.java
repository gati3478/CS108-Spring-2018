package com.freeuni.oop.seminar12.core.dao;

import com.freeuni.oop.seminar12.core.bean.Course;
import com.freeuni.oop.seminar12.core.bean.Student;
import com.freeuni.oop.seminar12.core.db.DbContract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@SuppressWarnings("WeakerAccess")
public class CourseDao {

    private final DataSource ds;

    public CourseDao(DataSource ds) {
        this.ds = ds;
    }

    public Course getCourseById(int studentId) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            // TODO: use DbContract instead of hardcoded table column names
            String query = "SELECT c.* FROM courses c WHERE c.course_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentId);

                try (ResultSet result = stmt.executeQuery()) {
                    if (result.next()) {
                        return fetchCourse(result);
                    } else {
                        throw new IllegalArgumentException("Invalid course id");
                    }
                }
            }
        }
    }

    public List<Course> getCourseList() throws SQLException {
        try (Connection conn = ds.getConnection()) {
            // TODO: use DbContract instead of hardcoded table column names
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT c.* FROM courses c ORDER BY c.course_id DESC";
                try (ResultSet result = stmt.executeQuery(query)) {
                    List<Course> list = new ArrayList<>();

                    while (result.next()) {
                        list.add(fetchCourse(result));
                    }

                    return list;
                }
            }
        }
    }

    public Student getStudentCourses(int studentId) throws SQLException {
        StudentDao studentDao = new StudentDao(ds);

        Student student = studentDao.getStudentById(studentId);

        try (Connection conn = ds.getConnection()) {
            // TODO: use DbContract instead of hardcoded table column names
            String query = "SELECT c.* FROM courses c, student_courses sc "
                    + "WHERE c.course_id = sc.course_id AND sc.student_id = ? "
                    + "ORDER BY c.course_id DESC";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentId);

                try (ResultSet result = stmt.executeQuery()) {
                    while (result.next()) {
                        student.getCourses().add(fetchCourse(result));
                    }
                }
            }
        }

        return student;
    }

    private Course fetchCourse(ResultSet result) throws SQLException {
        Course course = new Course();
        course.setCourseId(result.getInt(DbContract.CoursesTable.COLUMN_NAME_ID));
        course.setCourseName(result.getString(DbContract.CoursesTable.COLUMN_NAME_COURSE_NAME));
        course.setCourseCredit(result.getInt(DbContract.CoursesTable.COLUMN_NAME_CREDIT));
        course.setCourseType(result.getString(DbContract.CoursesTable.COLUMN_NAME_TYPE));
        return course;
    }

}
