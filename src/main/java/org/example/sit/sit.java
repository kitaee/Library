package org.example.sit;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class sit {

    private final Connection conn = DBConnection.conn;

    public void create(String status, String borrowDate, String returnDate, Long user_id, String book_id) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO borrow (status, borrow_date, return_date, user_id, book_id) VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,status);
            ps.setString(2, borrowDate);
            ps.setString(3, returnDate);
            ps.setLong(4,user_id);
            ps.setString(5,book_id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
