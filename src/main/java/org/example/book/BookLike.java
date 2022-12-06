package org.example.book;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookLike {
    private final Connection conn = DBConnection.conn;

    public void create(Long user_id, String book_id) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO book_like (user_id,book_id) VALUES (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,user_id);
            ps.setString(2,book_id);
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

    public void readById(Long user_id) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT * FROM book_like WHERE user_id = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1,user_id);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    // rs.getString(x) 일 경우 x번째 column에 있는 값을 String으로 가져온다는 의미
                    // rs.getInt(x)는 x번째 column의 값을 int로 가져옴
                    System.out.println("result : " + rs.getString("user_id") + " "+ rs.getString("book_id"));
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

    public void deleteById(Long user_id, String book_id) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM book_like WHERE book_id = ? and user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,book_id);
            ps.setLong(2,user_id);
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


    public void selectAll(){
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM book_like";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s", "user_id", "book_id");
            System.out.println();
            while (rs.next()){
                System.out.format("%-20s %-20s",
                        rs.getString("user_id"), rs.getString("book_id"));
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
