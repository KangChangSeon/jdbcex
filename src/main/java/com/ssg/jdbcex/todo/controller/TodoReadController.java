package com.ssg.jdbcex.todo.controller;

import com.ssg.jdbcex.todo.domain.TodoVO;
import com.ssg.jdbcex.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TodoReadController", urlPatterns = "/todo/read/*")
@Log4j2
public class TodoReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet ... Read");

        Long tno = Long.valueOf(req.getParameter("tno"));

        TodoVO dto = null;
        try {
            dto = TodoService.INSTANCE.get(tno);

            // 모델 담기
            req.setAttribute("dto", dto);

            //cookie 찾기
            Cookie viewToDoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewToDoCookie.getValue();
            boolean exists = false;
            if (todoListStr != null && todoListStr.indexOf(tno + "-") >= 0) {
                exists = true;
            }

            log.info("exists:" + exists);

            if (!exists) {
                todoListStr += tno + "-";
                viewToDoCookie.setValue(todoListStr);
                viewToDoCookie.setMaxAge(60 * 60 * 24);
                viewToDoCookie.setPath("/");
                resp.addCookie(viewToDoCookie);
            }
            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Cookie findCookie(Cookie[] cookies, String cookiename) {
        Cookie cookie = null;

        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if(c.getName().equals(cookiename)) {
                    cookie = c;
                    break;
                }
            }
        }

        if (cookie == null) {
            cookie = new Cookie(cookiename, "");
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24);
        }
        return cookie;
    }
}
