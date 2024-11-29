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
        try {
            // 요청에서 파라미터 값 가져오기
            String mid = request.getParameter("mid");
            String mpw = request.getParameter("mpw");
            String name = request.getParameter("name");
            String dueDateParam = request.getParameter("dueDate");
            String finishedParam = request.getParameter("finished");

            // 필수 값 검증
            if (mid == null || mid.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "아이디는 필수 항목입니다.");
                return;
            }
            if (mpw == null || mpw.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "비밀번호는 필수 항목입니다.");
                return;
            }

            boolean finished = "on".equals(finishedParam);

            LocalDate dueDate = null;
            if (dueDateParam != null && !dueDateParam.isEmpty()) {
                dueDate = LocalDate.parse(dueDateParam, DATE_TIME_FORMATTER);
            }

            // DTO 생성
            MemberDTO memberDTO = MemberDTO.builder()
                    .mid(mid)
                    .mpw(mpw)
                    .name(name)
                    .dueDate(dueDate)
                    .finished(finished)
                    .build();

            // 서비스 호출
            memberService.register(memberDTO);

            // 성공 시 리스트 페이지로 이동
            response.sendRedirect("/member/list");
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스 오류: " + e.getMessage(), e);
        }
    }

}
