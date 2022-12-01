package org.example.post;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Board {
    private final Connection conn = DBConnection.conn;

    public void createBoard(String name) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //아래 방식보다 string.format이 나을지도 맘대루
            String sql = "INSERT INTO board (name) VALUES (\"" + name + "\")";

            ps = conn.prepareStatement(sql);
            ps.execute();
            try {
                while (rs.next()) {
                    System.out.println("1");
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

    public void readBoard(String name) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //아래 방식보다 string.format이 나을지도 맘대루
            String sql = "SELECT * FROM board WHERE name = \"" + name + "\"";

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

    public void updateBoard(Long boardId, String newName) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //아래 방식보다 string.format이 나을지도 맘대루
            String sql = "UPDATE board SET name = \"" + newName + "\" WHERE board_id = " + boardId;

            ps = conn.prepareStatement(sql);
            ps.execute();
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

    public void deleteBoard(Long boardId) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //아래 방식보다 string.format이 나을지도 맘대루
            String sql = "DELETE FROM board WHERE board_id = " + boardId;

            ps = conn.prepareStatement(sql);
            ps.execute();
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

}
