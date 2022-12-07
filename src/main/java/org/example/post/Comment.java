package org.example.post;

import org.example.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment {
    private final Connection conn = DBConnection.conn;

    public void create(String content, Long userId, Long postId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("INSERT INTO comment (content, user_id, post_id) " +
                    "VALUES ('%s', %d, %d)", content, userId, postId);

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

    public void readByPostId(Long postId) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String sql = String.format("SELECT * FROM comment WHERE post_id = %d", postId);

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

    public void update(Long commentId, String newComment) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("UPDATE comment SET content = '%s' WHERE comment_id = %d" , newComment, commentId);

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

    public void delete(Long commentId) {
        PreparedStatement ps = null;
        try {
            String sql = String.format("DELETE FROM comment WHERE comment_id = %d", commentId);

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
