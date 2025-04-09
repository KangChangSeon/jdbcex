package com.ssg.jdbcex.todo.filter;

import com.ssg.jdbcex.todo.dto.MemberDTO;
import com.ssg.jdbcex.todo.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = {"/todo/*"}) // todo 안에 있는 모든 패키지에 적용
@Log4j2
public class LoginCheckFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        log.info("Login....doFilter....");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        if (session.getAttribute("loginInfo") != null) {
            chain.doFilter(req, resp);
        }

        // session 에 loginInfo 값이 없다면, 쿠키를 체크
        Cookie cookie = findCookie(((HttpServletRequest) req).getCookies(), "remember-me");

        // 세션에도 없고 쿠키도 없다면 그냥 로그인 으로 보냄
        if (cookie == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // 쿠키가 존재하는 상황
        log.info("cookie 가 있는 상황....doFilter....");
        String uuid = cookie.getValue();

        try {
            MemberDTO dto = MemberService.INSTANCE.getByUUID(uuid);
            log.info("cookie 의 값으로 조회한 사용자 정보" + dto);

            if (dto == null) {
                throw new Exception("Cookie is not found");
            }
            // 회원정보를 세션에 추가
            session.setAttribute("loginInfo", dto);
            chain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }

    private Cookie findCookie(Cookie[] cookies, String cookiename) {
        Cookie cookie = null;

        if (cookies == null || cookies.length == 0) {
            return null;
        }
        Optional<Cookie> result = Arrays.stream(cookies).filter(c -> c.getName().equals(cookiename)).findFirst();
        return result.isPresent() ? result.get() : null;
    }
}
