package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.service.FoodService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "FoodDeleteController", urlPatterns = "/food/delete")
public class FoodDeleteController extends HttpServlet {
    private FoodService foodService = FoodService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long fno = Long.valueOf(request.getParameter("fno"));
        //
        log.info("doPost FoodDeleteController 확인");
        try {
            foodService.delete(fno);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/food/list");
    }
}
