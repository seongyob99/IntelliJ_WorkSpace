package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@Log4j2
public class TodoServiceTest {
    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }


    // 추가
    @Test
    public void testInsert() throws SQLException {
        // 더미 데이터, 화면에서 전달 받은 TodoDTO
        TodoDTO todoDTO = TodoDTO.builder()
                .title("샘플 작업 1126")
                .dueDate(LocalDate.now())
                .build();
        todoService.register(todoDTO);
    }

    // 전체 조회
    @Test
    public void testSelectAll() throws SQLException {
        List<TodoDTO> dtoList= todoService.listAll();
        for (TodoDTO todoDTO:dtoList) {
            log.info(todoDTO);
        }
    }

    // 한개 조회
    @Test
    public void testSelectOne() throws SQLException {
        TodoDTO todoDTO = todoService.get(8L);
        log.info("하나 조회 todoDTO" + todoDTO);
    }

    // 수정
    @Test
    public void testUpdateOne() throws SQLException {
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(14L)
                .title("수정된 내용입니다.")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();
        todoService.update(todoDTO);

        log.info("하나 조회. + todoDTO" + todoDTO);
    }

    // 삭제
    @Test
    public void testDelteOne() throws SQLException {
        todoService.delete(12L);
    }
}
