package org.example.user;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WarningHistory {
    private final Connection conn = DBConnection.conn;

    public void create(String reason, Long userId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("INSERT INTO warning_history (reason, user_id) VALUES ('%s', %d)", reason, userId);

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

    public void readByUserId(Long userId) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = String.format("SELECT * FROM warning_history WHERE user_id = %d", userId);

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

    public void updateById(Long warningHistoryId, String reason) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("UPDATE warning_history SET reason = '%s' WHERE warning_history_id = %d", reason, warningHistoryId);

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

    public void deleteById(Long warningHistoryId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("DELETE FROM warning_history WHERE warning_history_id = %d", warningHistoryId);

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
