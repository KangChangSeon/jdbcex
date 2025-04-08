package com.ssg.jdbcex.todo.dao;

import com.ssg.jdbcex.todo.domain.TodoVO;
import lombok.Cleanup;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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

    // tbl_todo 테이블에 todo 를 넣는 Insert 기능(TodoVO vo)
    public void insert(TodoVO vo) throws SQLException {
        // 쿼리문 생성
        String sql = "INSERT INTO tbl_todo(title,duedate,finished) VALUES(?,?,?)";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, vo.getTitle());
        ps.setDate(2, Date.valueOf(vo.getDuedate()));
        ps.setBoolean(3, vo.isFinished());

        ps.executeUpdate();
    }

    public List<TodoVO> selectAll() throws Exception{
        List<TodoVO> list = new ArrayList<>();
        String sql = "Select * FROM tbl_todo";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
//                    .duedate(rs.getDate("duedate").toLocalDate().atStartOfDay())
                    .duedate(rs.getDate("duedate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
            list.add(vo);
        }
        return list;
    }

    // todoVO 삭제 한개
    public void deleteOne(Long tno) throws Exception {
        String sql = "DELETE FROM tbl_todo WHERE tno=?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, tno);
        ps.executeUpdate();
    }

    // todoVO 업데이트 한개
    public void updateOne(TodoVO vo) throws Exception {
        String sql = "UPDATE tbl_todo SET title=?, duedate=?, finished=? WHERE tno=?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, vo.getTitle());
        ps.setDate(2, Date.valueOf(vo.getDuedate()));
        ps.setBoolean(3, vo.isFinished());
        ps.setLong(4,vo.getTno());
        ps.executeUpdate();
    }
}
