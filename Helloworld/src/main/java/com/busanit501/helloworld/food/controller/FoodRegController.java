package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;
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

@WebServlet(name = "FoodRegController" ,urlPatterns = "/food/register")
public class FoodRegController extends HttpServlet {

    private FoodService foodService = FoodService.INSTANCE;
    //날짜
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/food/foodReg.jsp");
        dispatcher.forward(request,response);
    }

    //글 작성 로직
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FoodDTO foodDTO = FoodDTO.builder()
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),DATE_TIME_FORMATTER))
                .build();
        try {
            foodService.register(foodDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("doPost : 글쓰기 처리하는 로직, 디비 연결 전, 리스트로 이동함");
        response.sendRedirect("/food/list");
    }

}
