package com.ssg.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTests {
    // @Test 를 적용하는 메소드는 반드시 public, 파라미터나 리턴 타입 없이 작성한다.
    @Test
    public void test1() {
        int v1 = 10;
        int v2 = 10;
        // v1 과 v2 가 같은지 여부 확인
        // assertEquals() 의 의미는 "같다고 확신하다"
        Assertions.assertEquals(v1, v2);
    }

    @Test
    public void test2() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/ssgdb?serverTimezone=Asia/Seoul";
        String username = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, username, password);
        Assertions.assertNotNull(conn);
    }

    @Test
    public void test3() throws SQLException, ClassNotFoundException {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ssgdb?serverTimezone=Asia/Seoul");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        // HikariDataSource --> ConnectionPool 객체
        HikariDataSource ds = new HikariDataSource(config);
        Connection conn = ds.getConnection();
        Assertions.assertNotNull(conn);
        System.out.println(conn);
        conn.close();
    }
}
