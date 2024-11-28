package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.food.dao.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.jdbcex.dto.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
                .title("김밥")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        foodDAO.insert(foodVO); // 데이터 삽입
        System.out.println("데이터 삽입 완료: " + foodVO);
    }

    @Test
    public void testList() throws SQLException {
        List<FoodVO> list = foodDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void getOneTest() throws SQLException {
        Long fno = 5L;
        FoodVO foodVO = foodDAO.selectOne(fno);
        System.out.println(foodVO);
    }


    @Test
    public void deleteTest() throws SQLException {
        Long fno = 2L;
        foodDAO.deleteFood(fno);
    }

    @Test
    public void updateTest() throws SQLException {
        FoodVO foodVO = FoodVO.builder()
                .fno(6L)
                .title("수정 테스트 중")
                .finished(true)
                .dueDate(LocalDate.now())
                .build();
        foodDAO.updateOne(foodVO);
    }
}



