package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static String dburl = "jdbc:mysql://localhost:3306/Library";
    private static String dbUser = "root";
    private static String dbpasswd = "caucse!18";

    private static Connection conn;

    public static void main(String[] args) {
            Connection conn = null;

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

        printMenu();

        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while(true){
            choice = sc.nextInt();
            if(choice == 0) return;
            switch (choice){
                case 1:
                    borrowBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    useReadingRoom();
                    break;
            }
        }

    }

    public static void printMenu(){
        System.out.println("LIBRARY");
        System.out.println("1. borrow book");
        System.out.println("2. return book");
        System.out.println("3. use reading room");
    }

    public static void borrowBook() {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    System.out.println("1");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void returnBook(){
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "select * from user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    System.out.println("1");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void useReadingRoom(){
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "select * from user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    System.out.println("1");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}