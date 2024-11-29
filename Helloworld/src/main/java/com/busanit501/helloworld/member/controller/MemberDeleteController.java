package com.busanit501.helloworld.member.controller;

import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2 // log.info("이런 형식으로 출력 한다.")
@WebServlet(name = "MemberDeleteController", urlPatterns = "/member/delete")
public class MemberDeleteController extends HttpServlet {
    // 외주 일 시키기, 누구? 서비스 한테, 선언만,
    private MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mid = request.getParameter("mid"); // 파라미터명 수정
        if (mid == null || mid.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'mid' parameter");
            return;
        }

        log.info("doPost MemberDeleteController 확인: mid=" + mid);
        try {
            memberService.delete(mid); // String형 mid 전달
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/member/list");
    }

}
