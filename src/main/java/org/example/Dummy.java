package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dummy {
    private final Connection conn = DBConnection.conn;

    public void createBookRelatedDummy(int num) {
        PreparedStatement ps = null;
        try {
            String sql = "CALL make_dummy_book(?);";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,num);
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

    public void createUserDummy(int num) {
        PreparedStatement ps = null;
        try {
            String sql = "CALL make_dummy_user(?);";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,num);
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

    public void truncateAllTable() {
        PreparedStatement ps = null;
        try {
            String sql = "CALL truncate_all();";
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
