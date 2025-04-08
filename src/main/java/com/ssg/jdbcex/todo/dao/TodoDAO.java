package com.ssg.jdbcex.todo.dao;

import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoDAO {
    // DB 로부터 시간 얻어오는 간단한 기능 구현
    public String getTime() {
        String now = null;
        try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement ps = conn.prepareStatement("select now()");
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            now = rs.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws SQLException {
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement("select now()");
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();
        String now = rs.getString(1);
        return now;
    }
}
