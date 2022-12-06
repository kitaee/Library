package org.example.book;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Overdue {
    private final Connection conn = DBConnection.conn;

    public void create(Long borrow_id, int overdue_day, int overdue_fee) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO overdue (borrow_id, overdue_day, overdue_fee) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,borrow_id);
            ps.setInt(2,overdue_day);
            ps.setInt(3,overdue_fee);
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

    public void readById(Long borrow_id) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT * FROM overdue WHERE borrow_id = ?";

            ps = conn.prepareStatement(sql);
            ps.setLong(1,borrow_id);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    // rs.getString(x) 일 경우 x번째 column에 있는 값을 String으로 가져온다는 의미
                    // rs.getInt(x)는 x번째 column의 값을 int로 가져옴
                    System.out.println("result : " + rs.getLong("borrow_id") + " "+ rs.getString("overdue_day") + " "+ rs.getString("overdue_fee") );
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

    public void readByUserId(Long user_id) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT * FROM overdue JOIN borrow ON overdue.borrow_id = borrow.borrow_id WHERE borrow.user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,user_id);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    // rs.getString(x) 일 경우 x번째 column에 있는 값을 String으로 가져온다는 의미
                    // rs.getInt(x)는 x번째 column의 값을 int로 가져옴
                    System.out.println("result : " + rs.getLong("borrow_id") + " "+ rs.getString("overdue_day") + " "+ rs.getString("overdue_fee") );
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


    public void updateById(Long borrow_id, String overdue_day, String overdue_fee) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE overdue SET overdue_day=?,overdue_fee=? WHERE borrow_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,overdue_day);
            ps.setString(2, overdue_fee);
            ps.setLong(3, borrow_id);
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

    public void deleteById(Long borrow_id) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM overdue WHERE borrow_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,borrow_id);
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
            String sql = "SELECT * FROM overdue";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s", "BORROW_ID", "OVERDUE_DAY", "OVERDUE_FEE");
            System.out.println();
            while (rs.next()){
                System.out.format("%-20s %-20s %-20s",
                        rs.getString("borrow_id"), rs.getString("overdue_day"), rs.getString("overdue_fee"));
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
