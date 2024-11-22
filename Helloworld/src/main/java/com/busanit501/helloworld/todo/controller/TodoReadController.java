package com.busanit501.helloworld.todo.controller;

import com.busanit501.helloworld.todo.dto.TodoDTO;
import com.busanit501.helloworld.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TodoReadController",urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 클릭한 게시글 번호 가지고 와야함
        Long tno = Long.parseLong(request.getParameter("tno"));

        // 서비스에서 하나의 todo 더미 데이터 조회 후, 화면에 전달하기
        TodoDTO todoDTO = TodoService.INSTANCE.getOne(5L);
        request.setAttribute("dto", todoDTO);
        request.getRequestDispatcher("/WEB-INF/todo/read")
                .forward(request, response);

    }
}