package com.ssg.jdbcex.todo.dao;

import com.ssg.jdbcex.todo.domain.MemberVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

    public MemberVO getWithPassword(String mid, String mpw) throws Exception {
        String sql = "select * from tbl_member where mid=? and mpw=?";
        MemberVO vo = new MemberVO();

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, mid);
        ps.setString(2, mpw);

        @Cleanup ResultSet rs = ps.executeQuery();
        rs.next();
        vo = MemberVO.builder()
                .mid(mid)
                .mpw(mpw)
                .mname(rs.getString("mname")).build();
        return vo;
    }

    public void updateUUID(String mid, String uuid) throws Exception {
        String sql = "update tbl_member set uuid=? where mid=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, uuid);
        ps.setString(2, mid);
        ps.executeUpdate();
    }
}
