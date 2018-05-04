package com.freeuni.oop.seminar11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("FieldCanBeLocal")
public class ExampleAccess {

    private static String account = MyDBInfo.MYSQL_USERNAME;
    private static String password = MyDBInfo.MYSQL_PASSWORD;
    private static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
    private static String database = MyDBInfo.MYSQL_DATABASE_NAME;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://" + server, account, password);

            Statement stmt = con.createStatement();
            stmt.executeQuery("USE " + database);
            ResultSet rs = stmt.executeQuery("SELECT * FROM metropolises");

            // Sample Access Looking for Specific Item
            rs.absolute(3);
            System.out.println(rs.getString("metropolis"));

            // Sample Loop Access
            while (rs.next()) {
                String s = rs.getString("metropolis");
                int i = rs.getInt("population");
                System.out.println(s + "\t" + i);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
