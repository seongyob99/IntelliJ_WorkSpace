package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;
import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name = "FoodListController", urlPatterns = "/food/list")
public class FoodListController extends HttpServlet {
    private FoodService foodService = FoodService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        log.info("doGet FoodListController 확인");
        try {
            List<FoodDTO> foodList = foodService.INSTANCE.listAll();
            request.setAttribute("list", foodList);
            request.getRequestDispatcher("/WEB-INF/food/foodList.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}