package com.busanit501.helloworld.jdbcex.controller;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;

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

@WebServlet(name = "TodoReg2Controller" ,urlPatterns = "/todo/register2")
public class TodoReg2Controller extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    //날짜
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo/todoReg2.jsp");
        dispatcher.forward(request,response);
    }

    //글 작성 로직
    //화면에서 데이터 전달받고 -> DTO -> 서비스로 전달
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG 패턴
        //Post 처리후 리다이렉트 Get으로 호출, 무한 post 방지효과, 화면 전환 효과
        //임시로 담을 DTO 인스턴스 필요
        TodoDTO todoDTO = TodoDTO.builder()
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),DATE_TIME_FORMATTER))
                .build();
        // Controller -> Service
        try {
            todoService.register(todoDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
