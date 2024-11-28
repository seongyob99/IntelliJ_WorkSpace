package com.busanit501.helloworld.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="LoginResultController", urlPatterns = "/login/result")
public class LoginResultController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost,LoginResultController 서블릿 경유해서 로직 처리함");
        String ID = request.getParameter("ID");
        String PW = request.getParameter("PW");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>ID : " + ID + "</h1>");
        out.println("<h1>PW : " + PW + "</h1>");
        out.println("</body></html>");

    }

}
