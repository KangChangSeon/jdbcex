package com.ssg.jdbcex.todo.controller;

import com.ssg.jdbcex.todo.dao.TodoDAO;
import com.ssg.jdbcex.todo.domain.TodoVO;
import com.ssg.jdbcex.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "TodoModifyController", urlPatterns = "/todo/modify")
public class TodoModifyController extends HttpServlet {
    TodoDAO todoDAO = new TodoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet ... Modify");

        Long tno = Long.valueOf(req.getParameter("tno"));

        TodoVO dto = null;
        try {
            dto = TodoService.INSTANCE.get(tno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("dto", dto);
        req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost ... Modify");

        Long tno = Long.valueOf(req.getParameter("tno"));
        String title = req.getParameter("title");
        Date duedate = Date.valueOf(req.getParameter("duedate"));
        Boolean finished = Boolean.valueOf(req.getParameter("finished"));

        TodoVO vo = TodoVO.builder()
                .tno(tno)
                .title(title)
                .duedate(duedate.toLocalDate())
                .finished(finished)
                .build();
        try {
            todoDAO.updateOne(vo);
            resp.sendRedirect("/todo/list");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
