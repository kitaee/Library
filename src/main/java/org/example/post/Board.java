package org.example.post;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Board {
    private final Connection conn = DBConnection.conn;

    public void create(String name) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("INSERT INTO board (name) VALUES ('%s')", name);

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

    public void readByName(String name) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = String.format("SELECT * FROM board WHERE name = '%s'", name);

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

    public void updateById(Long boardId, String newName) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("UPDATE board SET name = '%s' WHERE board_id = '%d'", newName, boardId);

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

    public void deleteById(Long boardId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("DELETE FROM board WHERE board_id = %d", boardId);

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
