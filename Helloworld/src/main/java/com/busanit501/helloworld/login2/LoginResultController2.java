package com.busanit501.helloworld.login2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="LoginResultController2", urlPatterns = "/login2/result")
public class LoginResultController2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ID = request.getParameter("ID");
        String PW = request.getParameter("PW");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>ID : " + ID + "</h1>");
        out.println("<h1>PW : " + PW + "</h1>");
        out.println("</body></html>");

    }

}
