package com.busanut501.springpractice.service;

import com.busanit501.springpratice.dto.FoodDTO;
import com.busanit501.springpratice.service.FoodService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class FoodServiceTest {

    // 방법1
    @Autowired
    private FoodService foodService;

    @Test
    public void testRegister() {
        FoodDTO todoDTO = FoodDTO.builder()
                .title("실습 체크용 더미")
                .dueDate(LocalDate.now())
                .writer("안성엽")
                .build();
        foodService.register(todoDTO);
    } //

    @Test
    public void testGetAll() {
        List<FoodDTO> list = foodService.getAll();
        for (FoodDTO foodDTO:list) {
            log.info("foodDTO : " + foodDTO);
        }
    } //

    @Test
    public void testGetOne() {
        FoodDTO foodDTO = foodService.getOne(8L);
    }

    @Test
    public void testelete() {
        foodService.delete(19L);
    }

    @Test
    public void testUpdate() {
       FoodDTO todoDTO = FoodDTO.builder()
                .fno(20L)
                .title("수정 제목")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();
        foodService.update(todoDTO);
    }

}//
