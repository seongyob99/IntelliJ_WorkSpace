package com.busanit501.helloworld.member.controller;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name = "MemberListController", urlPatterns = "/member/list")
public class MemberListController extends HttpServlet {
    private MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        String result = (String) context.getAttribute("appTestName");
        log.info("MemberListController ServletContext : 값 조회 확인중 : " + result);


        log.info("doGet MemberListController 확인");

        // 쿠키 확인
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMe".equals(cookie.getName())) {
                    log.info("리스트 페이지에서 발견된 쿠키 rememberMe: {}", cookie.getValue());
                }
            }
        }

        try {
            List<MemberDTO> memberList = memberService.listAll();
            request.setAttribute("list", memberList);
            request.getRequestDispatcher("/WEB-INF/member/memberList.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            log.error("DB 오류 발생: ", e);
            response.sendRedirect("/error");
        }
    }
}
