package com.ssg.service;

import com.ssg.jdbcex.todo.dto.TodoDTO;
import com.ssg.jdbcex.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Log4j2
public class TodoServiceTests {
    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void testRegister() throws Exception {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Sample Test Register TodoVO3")
                .duedate(LocalDate.now())
                .finished(true)
                .build();
        log.info("-------------------------------------");
        log.info(todoDTO);
        log.info("-------------------------------------");
        todoService.register(todoDTO);
    }
}
