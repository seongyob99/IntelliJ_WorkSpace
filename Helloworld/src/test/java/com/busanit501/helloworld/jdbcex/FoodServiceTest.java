package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@Log4j2
public class FoodServiceTest {
    private FoodService foodService;

    @BeforeEach
    public void ready() {
        foodService = FoodService.INSTANCE;
    }


    // 추가
    @Test
    public void testInsert() throws SQLException {
        FoodDTO foodDTO = FoodDTO.builder()
                .title("샘플 작업 1126")
                .dueDate(LocalDate.now())
                .build();
        foodService.register(foodDTO);
    }


    // 전체 조회
    @Test
    public void testSelectAll() throws SQLException {
        List<FoodDTO> dtoList= foodService.listAll();
        for (FoodDTO foodDTO:dtoList) {
            log.info(foodDTO);
        }
    }

    // 하나 조회
    @Test
    public void testSelectOne() throws SQLException {
        FoodDTO foodDTO = foodService.get(8L);
        log.info("하나 조회 foodDTO" + foodDTO);
    }

    // 수정
    @Test
    public void testUpdateOne() throws SQLException {
        FoodDTO foodDTO = FoodDTO.builder()
                .fno(14L)
                .title("수정된 내용입니다.")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();
        foodService.update(foodDTO);

        log.info("하나 조회. + foodDTO" + foodDTO);
    }

    // 삭제
    @Test
    public void testDeleteOne() throws SQLException {
        foodService.delete(12L);
    }

}