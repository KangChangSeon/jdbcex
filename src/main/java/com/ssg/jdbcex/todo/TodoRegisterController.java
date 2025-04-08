package com.ssg.jdbcex.todo;

import com.ssg.jdbcex.todo.dao.TodoDAO;
import com.ssg.jdbcex.todo.service.TodoService;
import com.sun.tools.javac.comp.Todo;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "TodoRegisterController", urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {
    private TodoDAO todoDAO = new TodoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet 입력화면 볼수 있도록 구성");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/todo/register.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("입력을 처리하고 목록 페이지로 이동할거야");

        todoDAO.insert();
        resp.sendRedirect("/todo/list");
    }
}
