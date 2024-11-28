package com.busanit501.helloworld.member.controller;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "MemberRegController" ,urlPatterns = "/member/register")
public class MemberRegController extends HttpServlet {

    private MemberService memberService = MemberService.INSTANCE;
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/memberReg.jsp");
        dispatcher.forward(request,response);
    }

    //글 작성 로직
    //화면에서 데이터 전달받고 -> DTO -> 서비스로 전달
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDTO memberDTO = MemberDTO.builder()
                .name(request.getParameter("name"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),DATE_TIME_FORMATTER))
                .build();
        // Controller -> Service
        try {
            memberService.register(memberDTO);
            response.sendRedirect("/member/list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
