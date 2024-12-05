package com.busanut501.springpractice.mapper;

import com.busanit501.springpratice.domain.FoodVO;
import com.busanit501.springpratice.mapper.FoodMapper;
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
public class FoodMapperTest {
    @Autowired(required = false)
    private FoodMapper foodMapper;

    @Test
    public void testGetTime() {
        log.info("getTime : " + foodMapper.getTime());
    }

    @Test
    public void testInsert() {
        FoodVO foodVO = FoodVO.builder()
                .title("실습 체크용 더미")
                .dueDate(LocalDate.now())
                .writer("안성엽")
                .build();
        foodMapper.insert(foodVO);
    }

    @Test
    public void testSelectAll() {
        List<FoodVO> lists = foodMapper.selectAll();
        for (FoodVO foodVO:lists) {
            log.info("foodVO : " + foodVO);
        }
    }

    @Test
    public void testSelectOne() {
        FoodVO foodVO = foodMapper.selectOne(13L);
    }

    @Test
    public void testDelete() {
        foodMapper.delete(6L);
    }

    @Test
    public void testUpdate() {
        FoodVO foodVO = FoodVO.builder()
                .fno(5L)
                .title("음식 수정 테스트")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();
    }
}
