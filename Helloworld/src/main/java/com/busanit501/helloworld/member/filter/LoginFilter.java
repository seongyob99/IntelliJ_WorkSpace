package com.busanit501.helloworld.member.filter;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


//톰캣 서버에서,
// 한글로 입력된 내용을, UTF8로 변환해서 보내기.
// 필터, 서버에 작업을 실행하기전에, 먼저 검사한다.
// 유효성 체크.
@WebFilter(urlPatterns = {"/member/*"})
@Log4j2
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter: /member/* 하위로 들어오는 모든 URL에 대해 로그인 체크");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        // 로그인 페이지 요청은 필터를 통과
        String requestURI = request.getRequestURI();
        if (requestURI.equals(request.getContextPath() + "/member/login")) {
            log.info("로그인 페이지 요청이므로 필터를 통과");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 세션에 로그인 정보가 없는 경우
        if (session.getAttribute("loginInfo") == null) {
            log.info("세션에 로그인 정보가 없습니다. 자동 로그인 시도");

            // 자동 로그인: 쿠키에서 UUID 확인
            Cookie findCookie = findCookie(request.getCookies(), "rememberMe");
            if (findCookie == null) {
                log.info("자동 로그인 실패: rememberMe 쿠키가 없습니다.");
                response.sendRedirect(request.getContextPath() + "/member/login");
                return; // 리다이렉트 후 필터 종료
            }

            String getUuid = findCookie.getValue();

            try {
                MemberDTO memberDTO = MemberService.INSTANCE.getMemberWithUuidService(getUuid);
                if (memberDTO == null) {
                    log.info("자동 로그인 실패: 유효하지 않은 UUID");
                    response.sendRedirect(request.getContextPath() + "/member/login");
                    return; // 리다이렉트 후 필터 종료
                }

                // 자동 로그인 성공: 세션에 회원 정보 저장
                session.setAttribute("loginInfo", memberDTO);
                log.info("자동 로그인 성공: {}", memberDTO);
            } catch (SQLException e) {
                log.error("DB 오류 발생: ", e);
                response.sendRedirect(request.getContextPath() + "/member/login");
                return; // 리다이렉트 후 필터 종료
            }
        }

        // 세션에 로그인 정보가 있는 경우 계속 진행
        log.info("세션에 로그인 정보가 있습니다: {}", session.getAttribute("loginInfo"));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
