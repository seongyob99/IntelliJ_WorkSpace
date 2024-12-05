package com.busanit501.springex.sample.mapper;

import com.busanit501.springex.domain.TodoVO;
import com.busanit501.springex.dto.PageRequestDTO;
import com.busanit501.springex.dto.TodoDTO;
import com.busanit501.springex.mapper.TimeMapper;
import com.busanit501.springex.mapper.TodoMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTest {
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info("getTime : " + todoMapper.getTime());
    }

    @Test
    public void testInsert() {
        TodoVO todoVO = TodoVO.builder()
                .title("샘플 테스트")
                .dueDate(LocalDate.now())
                .writer("안성엽")
                .build();
        todoMapper.insert(todoVO);
    }
    @Test
    public void testselectAll() {
        List<TodoVO> lists = todoMapper.selectAll();
        for (TodoVO todoVO:lists) {
            log.info("todoVO : " + todoVO);
        }
    }

    @Test
    public void testselectONe() {
        TodoVO todoVO = todoMapper.selectOne(3L);
        log.info("todoVO : " + todoVO);

    }

    @Test
    public void testDelete() {
        todoMapper.delete(4L);
    }

    @Test
    public void testUpdate() {
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("수정 제목")
                .finished(true)
                .dueDate(LocalDate.now())
                .build();
        todoMapper.update(todoVO);
    }

    @Test
    public void testSelectAllWithPage() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

       List<TodoVO> list = todoMapper.selectList(pageRequestDTO);
       list.forEach(vo->log.info("vo : " + vo));
    }

    @Test
    public void testGetCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

       int total = todoMapper.getCount(pageRequestDTO);
       log.info("total : " + total);
    }
}
