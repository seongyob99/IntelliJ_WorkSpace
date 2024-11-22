package com.busanit501.helloworld.food.controller;


import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FoodReadController",urlPatterns = "/food/read")
public class FoodReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 클릭한 게시글 번호 가지고 와야함
        Long tno = Long.parseLong(request.getParameter("tno"));

        // 서비스에서 하나의 todo 더미 데이터 조회 후, 화면에 전달하기
        FoodDTO foodDTO = FoodService.INSTANCE.getOne(5L);
        request.setAttribute("dto", foodDTO);
        request.getRequestDispatcher("/WEB-INF/food/foodRead.jsp")
                .forward(request, response);

    }
}