package com.ssg.dao;

import com.ssg.jdbcex.todo.dao.TodoDAO;
import com.ssg.jdbcex.todo.domain.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

// 단위 테스트
public class TodoDAOTests {
    private TodoDAO todoDAO;

    @BeforeEach
    public void ready(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println(todoDAO.getTime());
    }

    @Test
    public void testTime1() throws Exception {
        System.out.println(todoDAO.getTime2());
    }

    @Test
    public void testInsert() throws Exception {
        TodoVO vo = TodoVO.builder()
                .title("다섯")
                .duedate(LocalDateTime.now())
                .finished(false)
                .build();
        todoDAO.insert(vo);
    }
}
