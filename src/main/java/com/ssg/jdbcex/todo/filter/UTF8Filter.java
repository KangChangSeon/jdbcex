package com.ssg.jdbcex.todo.filter;


import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
@Log4j2
public class UTF8Filter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        log.info("UTF8Filter....doFilter....");
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }
}
