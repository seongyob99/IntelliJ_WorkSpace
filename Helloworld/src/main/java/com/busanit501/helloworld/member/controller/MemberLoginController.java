package com.busanit501.helloworld.member.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "MemberLoginController", urlPatterns = "/member/login")
public class MemberLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("MemberLoginController doGet ");
        request.getRequestDispatcher("/WEB-INF/member/memberLogin.jsp")
                .forward(request, response);
    }

    // 로직 처리,
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("MemberLoginController doPost ");
        String mid = request.getParameter("mid");
        String pwd = request.getParameter("pwd");
        String tempInfo = mid+pwd;
        HttpSession session = request.getSession();
        session.setAttribute("loginInfo", tempInfo);
        response.sendRedirect("/member/list");

    }
}