package com.busanit501.helloworld.member.controller;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MemberReadController", urlPatterns = "/member/read")
public class MemberReadController extends HttpServlet {
    private static final String COOKIE_NAME = "viewMembers";
    private final MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doGet MemberReadController 하나 조회 예제");

        // `mid`를 String으로 처리
        String mid = request.getParameter("mid");

        // null 또는 빈 값 처리
        if (mid == null || mid.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'mid' parameter: null or empty");
            return;
        }

        try {
            // 서비스에서 `mid`로 데이터 조회
            MemberDTO memberDTO = memberService.get(mid.trim());
            request.setAttribute("dto", memberDTO);

            // 쿠키 생성 및 업데이트
            updateCookieWithViewedMember(request, response, mid);

            // JSP로 전달
            request.getRequestDispatcher("/WEB-INF/member/memberRead.jsp").forward(request, response);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred: " + e.getMessage());
        }
    }

    /**
     * 조회된 회원 ID를 기반으로 쿠키를 생성하거나 업데이트합니다.
     */
    private void updateCookieWithViewedMember(HttpServletRequest request, HttpServletResponse response, String mid) {
        Cookie cookie = findCookie(request.getCookies(), COOKIE_NAME);

        String cookieValue = cookie.getValue();
        boolean exists = false;

        // 쿠키 값에 현재 조회된 회원 ID가 있는지 확인
        if (cookieValue != null && cookieValue.contains(mid + "-")) {
            exists = true;
        }

        // 존재하지 않을 경우 추가
        if (!exists) {
            cookieValue += mid + "-";
            cookie.setValue(cookieValue);
            cookie.setMaxAge(60 * 60 * 24); // 24시간 유효
            cookie.setPath("/");
            response.addCookie(cookie); // 응답에 쿠키 추가
        }
    }

    /**
     * 특정 이름의 쿠키를 검색하거나 없으면 새로 생성합니다.
     */
    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie foundCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    foundCookie = cookie;
                    break;
                }
            }
        }

        // 쿠키가 없으면 새로 생성
        if (foundCookie == null) {
            foundCookie = new Cookie(name, "");
            foundCookie.setPath("/");
            foundCookie.setMaxAge(60 * 60 * 24); // 24시간 유효
        }

        return foundCookie;
    }
}
