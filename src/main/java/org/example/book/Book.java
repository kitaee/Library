package org.example.book;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
    private final Connection conn = DBConnection.conn;

    public void selectAll(){
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM book";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "BOOK ID", "NAME", "LOCATION", "ISBN", "CLASSIFICATION CODE", "PUBLISHER_ID");
            System.out.println();
            while (rs.next()){
                System.out.format("%-20s %-20s %-20s %-20s %-20s %-20s",
                       rs.getString("book_id"), rs.getString("name"), rs.getString("loaction")
                ,rs.getString("isbn"), rs.getString("classification_code"), rs.getLong("publisher_id"));
                System.out.println();
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null){
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
