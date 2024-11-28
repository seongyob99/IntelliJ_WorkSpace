package com.busanit501.helloworld.calc;

//서블릿 = 컨트롤러 역할.
//웹브라우저로 부터 들어온 요청에 대해서 화면을 제공 또는 데이터 전달하는 중간 정류장 역할. (관제탑)

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "calcInputController", urlPatterns = "/calc/input")
//웹 브라우저에서, 주소 : http://localhost:8080/calc/input
//이 파일이 응답 -> 입력화면으로 전달 해주는 역할.
// 화면에 직접 접근 X 서블릿 통해서 접근.
public class calcInputController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet 호출함.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/calc/calc_input.jsp");
        dispatcher.forward(request,response);
    }
}
