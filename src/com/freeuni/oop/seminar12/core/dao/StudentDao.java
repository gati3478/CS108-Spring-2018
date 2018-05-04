package com.freeuni.oop.seminar12.core.dao;

import com.freeuni.oop.seminar12.core.bean.Course;
import com.freeuni.oop.seminar12.core.bean.Student;
import com.freeuni.oop.seminar12.core.db.DbContract;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class StudentDao {

    private final DataSource ds;

    public StudentDao(DataSource ds) {
        this.ds = ds;
    }

    public Student getStudentById(int studentId) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            // TODO: use DbContract instead of hardcoded table column names
            String query = "SELECT s.* FROM students s WHERE s.student_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, studentId);

                try (ResultSet rslt = stmt.executeQuery()) {
                    if (rslt.next()) {
                        return fetchStudent(rslt);
                    } else {
                        throw new IllegalArgumentException("Invalid student id");
                    }
                }
            }
        }
    }

    public List<Student> getStudentList() throws SQLException {
        try (Connection conn = ds.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                // TODO: use DbContract instead of hardcoded table column names
                String query = "SELECT s.* FROM students s ORDER BY s.student_id DESC";
                try (ResultSet result = stmt.executeQuery(query)) {
                    List<Student> list = new ArrayList<>();

                    while (result.next()) {
                        list.add(fetchStudent(result));
                    }

                    return list;
                }
            }
        }
    }

    public Course getCourseStudents(int courseId) throws SQLException {
        CourseDao courseDao = new CourseDao(ds);

        Course course = courseDao.getCourseById(courseId);

        try (Connection conn = ds.getConnection()) {
            // TODO: use DbContract instead of hardcoded table column names
            String query = "SELECT s.* FROM students s, student_courses sc "
                    + "WHERE s.student_id = sc.student_id AND sc.course_id = ? "
                    + "ORDER BY s.student_id DESC";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, courseId);

                try (ResultSet result = stmt.executeQuery()) {
                    while (result.next()) {
                        course.getStudents().add(fetchStudent(result));
                    }
                }
            }
        }

        return course;
    }

    private Student fetchStudent(ResultSet result) throws SQLException {
        Student student = new Student();
        student.setStudentId(result.getInt(DbContract.StudentTable.COLUMN_NAME_ID));
        student.setIdnumber(result.getString(DbContract.StudentTable.COLUMN_NAME_ID_NUMBER));
        student.setFirstName(result.getString(DbContract.StudentTable.COLUMN_NAME_FIRSTNAME));
        student.setLastName(result.getString(DbContract.StudentTable.COLUMN_NAME_LASTNAME));
        student.setRegisterDate(new Date(result.getTimestamp(DbContract.StudentTable.COLUMN_NAME_REGISTRATION).getTime()));
        return student;
    }

}
