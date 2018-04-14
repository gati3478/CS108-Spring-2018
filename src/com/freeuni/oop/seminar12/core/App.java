package com.freeuni.oop.seminar12.core;

import com.freeuni.oop.seminar11.MyDBInfo;
import com.freeuni.oop.seminar12.core.bean.Course;
import com.freeuni.oop.seminar12.core.bean.Student;
import com.freeuni.oop.seminar12.core.dao.CourseDao;
import com.freeuni.oop.seminar12.core.dao.StudentDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        // Establishing connection to the database
        // TODO: NOTE: DO NOT USE THIS TECHNIQUE FOR ASSIGNMENT
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://" + MyDBInfo.MYSQL_DATABASE_SERVER + ":3306/" + MyDBInfo.MYSQL_DATABASE_NAME + "?characterEncoding=UTF8");
        ds.setUsername(MyDBInfo.MYSQL_USERNAME);
        ds.setPassword(MyDBInfo.MYSQL_PASSWORD);

        // Creating DAO classes
        StudentDao studentDao = new StudentDao(ds);
        CourseDao courseDao = new CourseDao(ds);

        // Using Gson class for pretty printing
        GsonBuilder gb = new GsonBuilder();
        gb.setPrettyPrinting();
        Gson gson = gb.create();

        // Printing all courses per student
        try {
            for (Student rec : studentDao.getStudentList()) {
                Student student = courseDao.getStudentCourses(rec.getStudentId());
                System.out.println(gson.toJson(student));
                System.out.println("-------------------------");
            }
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }

        System.out.print("\n\n\n");

        // Printing all students per course
        try {
            for (Course rec : courseDao.getCourseList()) {
                Course course = studentDao.getCourseStudents(rec.getCourseId());
                System.out.println(gson.toJson(course));
                System.out.println("-------------------------");
            }
        } catch (SQLException ex) {
            throw new AssertionError(ex);
        }
    }
}
