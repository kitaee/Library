package org.example.book;

import org.example.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;

public class Borrow {

    private final Connection conn = DBConnection.conn;

    public void selectAll(){
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM borrow";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "BORROW ID", "BORROW DATE", "RETURN DATE", "STATUS}", "USERID", "BOOK ID");
            System.out.println();
            while (rs.next()){
                System.out.format("%-20s %-20s %-20s %-20s %-20s %-20s",
                        rs.getString("borrow_id"), rs.getString("borrow_date"), rs.getString("return_date")
                        ,rs.getString("status"), rs.getString("user_id"), rs.getString("book_id"));
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

    public void extendReturnDate(Long id){
        PreparedStatement ps = null;
        Calendar cal = Calendar.getInstance();
        Date ReturnDate = getReturnDateById(id);
        cal.setTime(ReturnDate);
        cal.add(Calendar.DATE,7);
        try {
            String sql = "INSERT INTO extend_return_date VALUES (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            ps.setDate(2,new java.sql.Date(cal.getTime().getTime()));

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

    public void checkFee(Long id){
        PreparedStatement ps = null;
        try {
            String fee = "SELECT getLateFee(borrow_date, ?) as fee from" +
                    "(SELECT borrow_date FROM borrow where borrow_id=?) as s";
            ps = conn.prepareStatement(fee);
            ps.setString(1, LocalDate.now().toString());
            ps.setLong(2,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("Your fee is : "+rs.getString("fee")+" won\n");
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

    public void selectAllExtendList(){
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM extend_return_date";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s", "BORROW ID", "EXTENDED DATE");
            System.out.println();
            while (rs.next()){
                System.out.format("%-20s %-20s",
                        rs.getString("borrow_id"), rs.getString("extended_date"));
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

    public Date getReturnDateById(Long id) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Date ret = null;

        try {
            String sql = "SELECT * FROM borrow WHERE borrow_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            try {
                rs.next();
                ret = rs.getDate("return_date");
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
        return ret;
    }

    /*
    public void update(Long userId, String name, String email, String password, String type, String status) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("UPDATE user SET name = '%s', email = '%s', password = '%s', type = '%s', status = '%s'" +
                    " WHERE user_id = %d" , name, email, password, type, status, userId);

            ps = conn.prepareStatement(sql);
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
*/
/*
    public void delete(Long userId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("DELETE FROM user WHERE user_id = %d", userId);

            ps = conn.prepareStatement(sql);
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

 */
}
