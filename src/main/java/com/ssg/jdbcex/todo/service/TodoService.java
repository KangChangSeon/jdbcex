package com.ssg.jdbcex.todo.service;

import com.ssg.jdbcex.todo.dao.TodoDAO;
import com.ssg.jdbcex.todo.domain.TodoVO;
import com.ssg.jdbcex.todo.dto.TodoDTO;
import com.ssg.jdbcex.todo.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public enum TodoService {
    INSTANCE;

    //todoDAO 와 Service 연결
    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws SQLException {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        dao.insert(todoVO);
    }

    //Todo 전체 리스트 반환 기능
    public List<TodoDTO> getList() {
        List<TodoDTO> todoDTOList = IntStream.range(0, 10).mapToObj(i -> {
            TodoDTO todoDTO = new TodoDTO();
            todoDTO.setTno((long) i);
            todoDTO.setTitle("Todo..." + i);
            todoDTO.setDuedate(LocalDate.now());

            return todoDTO;
        }).collect(Collectors.toList());
        return todoDTOList;
    }

    //사용자가 선택한 Todo 1개 반환하는 기능
    public TodoVO get(Long tno) throws Exception{
        TodoDAO todoDAO = new TodoDAO();
        TodoVO vo = todoDAO.selectOne(tno);
        return vo;
    }

    public List<TodoDTO> listAll() throws Exception {
        List<TodoVO> voList = dao.selectAll();
        log.info("voList........");
        log.info(voList);

        List<TodoDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo,TodoDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

}
// enum 타입은 정해진 수만큼 객체를 생성할 수 있다.