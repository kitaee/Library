package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static String dburl = "jdbc:mysql://localhost:3306/너네 DB이름";
    public static String dbUser = "root";
    public static String dbpasswd = "너네 비밀번호";

    public static Connection conn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Cannot connect to DB");
            throw new RuntimeException(e);
        }
    }

}
