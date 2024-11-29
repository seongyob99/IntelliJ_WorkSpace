package com.busanit501.helloworld.member.controller;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name = "MemberUpdateController", urlPatterns = "/member/update")
public class MemberUpdateController extends HttpServlet {

    private MemberService memberService = MemberService.INSTANCE;
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String mid = request.getParameter("mid");
            if (mid == null || mid.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'mid' parameter");
                return;
            }

            MemberDTO memberDTO = memberService.get(mid); // String형 mid로 호출
            request.setAttribute("dto", memberDTO);

            request.getRequestDispatcher("/WEB-INF/member/memberUpd.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String mid = request.getParameter("mid");
            String name = request.getParameter("name");
            String mpw = request.getParameter("mpw");  // JSP에서 전달받는 비밀번호
            String dueDateParam = request.getParameter("dueDate");
            String finishedParam = request.getParameter("finished");

            // 유효성 검증
            if (mid == null || mid.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'mid' parameter");
                return;
            }
            if (mpw == null || mpw.trim().isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Password cannot be empty");
                return;
            }

            boolean finished = "on".equals(finishedParam);

            LocalDate dueDate = null;
            if (dueDateParam != null && !dueDateParam.isEmpty()) {
                dueDate = LocalDate.parse(dueDateParam, DATE_TIME_FORMATTER);
            }

            MemberDTO memberDTO = MemberDTO.builder()
                    .mid(mid)
                    .name(name)
                    .mpw(mpw)  // 비밀번호 추가
                    .dueDate(dueDate)
                    .finished(finished)
                    .build();

            memberService.update(memberDTO);

            response.sendRedirect("/member/list");
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }


}
