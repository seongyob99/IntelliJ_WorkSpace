package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.food.dao.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FoodDAOTest {
    private FoodDAO foodDAO;

    @BeforeEach
    public void ready() {
        foodDAO = new FoodDAO();
    }

    // 현재 시간 조회 테스트 (방법 1)
    @Test
    public void getTime() {
        String time = foodDAO.getTime();
        assertNotNull(time); // 결과 검증
        System.out.println("현재 시간 조회 (방법 1): " + time);
    }

    // 현재 시간 조회 테스트 (방법 2)
    @Test
    public void getTime2() throws SQLException {
        String time = foodDAO.getTime2();
        assertNotNull(time); // 결과 검증
        System.out.println("현재 시간 조회 (방법 2): " + time);
    }

    // 데이터 삽입 테스트
    @Test
    public void insertTest() throws Exception {
        FoodVO foodVO = FoodVO.builder()
                .title("돼지국밥")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        foodDAO.insert(foodVO); // 데이터 삽입
        System.out.println("데이터 삽입 완료: " + foodVO);
    }
}
