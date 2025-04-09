package com.ssg.jdbcex.todo;

import com.ssg.jdbcex.todo.dao.TodoDAO;
import com.ssg.jdbcex.todo.domain.TodoVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "TodoRemoveController", urlPatterns = "/todo/remove")
public class TodoRemoveController extends HttpServlet {
    TodoDAO todoDAO = new TodoDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost ... Modify");

        // 한글 폰트 깨져서 적용 (SSR 방식)
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Long tno = Long.valueOf(req.getParameter("tno"));
        try {
            todoDAO.deleteOne(tno);
            resp.sendRedirect("/todo/list");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
