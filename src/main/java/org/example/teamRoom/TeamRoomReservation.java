package org.example.teamRoom;

import org.example.DBConnection;

import java.sql.*;
import java.text.SimpleDateFormat;

public class TeamRoomReservation {
    private final Connection conn = DBConnection.conn;

    public void selectAll(){
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT tr.team_room_id,tr.name,trr.start_time,trr.end_time,trr.people_number,trr.user_id FROM team_room_reservation AS trr JOIN team_room AS tr ON trr.team_room_id = tr.team_room_id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "TEAM_ROOM_ID","TEAM_ROOM_NAME", "START_TIME", "END_TIME", "PEOPLE_NUM", "USER_ID");
            System.out.println();
            while (rs.next()){
                System.out.format("%-20s %-20s %-20s %-20s %-20s %-20s",
                        rs.getLong("team_room_id"),rs.getString("name"), rs.getString("start_time"),
                        rs.getString("end_time"),rs.getInt("people_number"), rs.getLong("user_id"));
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

    public void create(String start_time, String end_time, int people_number, Long team_room_id, Long user_id) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO team_room_reservation (start_time, end_time, people_number,team_room_id,user_id) VALUES (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,start_time);
            ps.setString(2,end_time);
            ps.setInt(3,people_number);
            ps.setLong(4,team_room_id);
            ps.setLong(5,user_id);
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
