package com.busanit501.helloworld.member.controller;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Log4j2
@WebServlet(name = "MemberLoginController", urlPatterns = "/member/login")
public class MemberLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("MemberLoginController doGet");
        request.getRequestDispatcher("/WEB-INF/member/memberLogin.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("MemberLoginController doPost");

        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");
        String auto = request.getParameter("auto");

        // "자동 로그인" 체크 여부
        boolean rememberMe = auto != null && auto.equals("on");

        try {
            // 로그인 서비스 호출
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid, mpw);

            if (memberDTO == null) {
                log.info("로그인 실패: 잘못된 ID 또는 비밀번호");
                response.sendRedirect("/member/login?result=fail");
                return;
            }

            log.info("로그인 성공: {}", memberDTO);

            // 세션에 사용자 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", memberDTO);

            // 자동 로그인 처리
            if (rememberMe) {
                String uuid = UUID.randomUUID().toString();
                MemberService.INSTANCE.updateUuid(mid, uuid);
                memberDTO.setUuid(uuid);

                // rememberMe 쿠키 생성 및 추가
                Cookie rememberCookie = new Cookie("rememberMe", uuid);
                rememberCookie.setPath("/");
                rememberCookie.setMaxAge(60 * 60 * 24 * 7); // 7일 유효
                rememberCookie.setHttpOnly(true); // JavaScript 접근 방지
                response.addCookie(rememberCookie);

                log.info("자동 로그인 활성화: UUID = {}", uuid);
            }

            // 로그인 성공 후 리스트 페이지로 이동
            response.sendRedirect("/member/list");
        } catch (SQLException e) {
            log.error("DB 오류 발생: ", e);
            response.sendRedirect("/member/login?result=error");
        } catch (Exception e) {
            log.error("알 수 없는 오류 발생: ", e);
            response.sendRedirect("/member/login?result=error");
        }
    }
}
