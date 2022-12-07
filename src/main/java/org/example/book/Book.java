package org.example.book;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
    private final Connection conn = DBConnection.conn;

    public void create(String name,String location,String isbn, String classification_code,Long publisher_id) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO book (name,location,isbn,classification_code,publisher_id) VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,location);
            ps.setString(3,isbn);
            ps.setString(4,classification_code);
            ps.setLong(5,publisher_id);
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

    public void readById(String id) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT * FROM book WHERE book_id = ?";

            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    // rs.getString(x) 일 경우 x번째 column에 있는 값을 String으로 가져온다는 의미
                    // rs.getInt(x)는 x번째 column의 값을 int로 가져옴
                    System.out.println("result : " + rs.getString("book_id") + " "+ rs.getString("name") + " "+ rs.getString("location") + " "+ rs.getString("isbn") + " " + rs.getString("classification_code") + " " + rs.getString("publisher_id"));
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

    public void updateById(String book_id, String name,String location,String isbn, String classification_code,Long publisher_id) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE book SET name = ?,location = ?,isbn = ?,classification_code = ?,publisher_id = ?  WHERE book_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,location);
            ps.setString(2, isbn);
            ps.setString(3, classification_code);
            ps.setLong(4, publisher_id);
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

    public void deleteById(String book_id) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM book WHERE book_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,book_id);
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

    public void monthlyBest(int month){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT monthlyBest(?) as monthlyBest";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, month);
            rs = ps.executeQuery();
            rs.next();
            System.out.println(month+"'s Monthly book is "+rs.getString("monthlyBest")+"\n");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally{

        }
    }

}
