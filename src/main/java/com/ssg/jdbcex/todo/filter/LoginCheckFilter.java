package com.ssg.jdbcex.todo.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/todo/*"}) // todo 안에 있는 모든 패키지에 적용
@Log4j2
public class LoginCheckFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        log.info("Login....doFilter....");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        if (session.getAttribute("loginInfo") == null) {
            log.info("로그인 정보가 없는 사용자");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, resp);
    }
}
