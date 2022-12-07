package org.example.user;

import org.example.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class VisitRecord {
    private static final int ENTER = 0;
    private static final int EXIT = 1;
    private final Connection conn = DBConnection.conn;

    public void enter(String time, Long userId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("INSERT INTO visit_record (time, type, user_id) " +
                                        "VALUES ('%s', %d, %d)", time, ENTER, userId);

            ps = conn.prepareStatement(sql);
            ps.execute();

            String sql1 = "SELECT checkEnterOverSit(?, ?) as r";
            ps = conn.prepareStatement(sql1);
            ps.setString(1, time);
            ps.setLong(2,userId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1)==0) {
                System.out.println("You exit library during more than 2 hour");
            }
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

    public void exit(String time, Long userId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("INSERT INTO visit_record (time, type, user_id) " +
                                        "VALUES ('%s', %d, %d)", time, EXIT, userId);

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

    public void selectAll() {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = "SELECT * FROM visit_record";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s", "VISIT RECORD ID", "TIME", "TYPE", "USERID");
            System.out.println();
            while (rs.next()){
                System.out.format("%-20s %-20s %-20s %-20s",
                        rs.getString("visit_record_id"), rs.getString("time"), rs.getString("type")
                        ,rs.getString("user_id"));
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

    public void readByUserId(Long userId) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = String.format("SELECT * FROM visit_record WHERE user_id = %d", userId);

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    // rs.getString(x) 일 경우 x번째 column에 있는 값을 String으로 가져온다는 의미
                    // rs.getInt(x)는 x번째 column의 값을 int로 가져옴
                    System.out.println(rs.getString(2));
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

    public void updateById(Long visitRecordId, int type) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("UPDATE visit_record SET type = %d WHERE visit_record_id = %d", type, visitRecordId);

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

    public void deleteById(Long visitRecordId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("DELETE FROM visit_record WHERE visit_record_id = %d", visitRecordId);

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
}
