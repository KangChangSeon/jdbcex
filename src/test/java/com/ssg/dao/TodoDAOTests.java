package com.ssg.dao;

import com.ssg.jdbcex.todo.dao.TodoDAO;
import com.ssg.jdbcex.todo.domain.TodoVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
                .duedate(LocalDate.now())
                .finished(false)
                .build();
        todoDAO.insert(vo);
    }

    @Test
    public void testList() throws Exception {
        List<TodoVO> voList = todoDAO.selectAll();

        Assertions.assertNotNull(voList);
        Assertions.assertFalse(voList.isEmpty());

        voList.forEach(System.out::println);
    }

    @Test
    public void testDelete() throws Exception {
        Long ctno = 9L;

        todoDAO.deleteOne(ctno);

        List<TodoVO> list = todoDAO.selectAll();
        boolean exists = list.stream().anyMatch(todo -> todo.getTno() == ctno);

        Assertions.assertFalse(exists,"삭제가 되지 않았습니다.");
    }

    @Test
    public void testUpdate() throws Exception {
        TodoVO vo = TodoVO.builder()
                .tno(4L)
                .title("수정")
                .duedate(LocalDate.now())
                .finished(false)
                .build();
        todoDAO.updateOne(vo);

        todoDAO.selectAll().forEach(System.out::println);
    }
}
