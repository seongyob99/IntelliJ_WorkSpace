package com.busanit501.helloworld.todo.service;

import com.busanit501.helloworld.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum TodoService {
    INSTANCE;

    // 글 등록 기능
    public void register(TodoDTO todoDTO) {
        // DB에 데이터 쓰는 작업, insert
        System.out.println("글쓰기 작업하는 기능 콘솔출력");
    }

    // 조회, 샘플 (하드코딩, 더미)
    public List<TodoDTO> getList() {
        // DB에서 데이터 조회 후 전달하기
        List<TodoDTO> todoList = IntStream.range(0, 10)
                .mapToObj(i -> {
                    TodoDTO todoDTO = new TodoDTO();
                    todoDTO.setTitle("test" + i);
                    todoDTO.setTno((long) i);
                    todoDTO.setDueDate(LocalDate.now());
                    return todoDTO; // todoDTO 객체 반환
                })
                .collect(Collectors.toList());

        return todoList; // 리스트 반환
    }

    //하나 조회, 상세보기, 게시글에서, 게시글 번호 클릭시 나타나는 데이터
    public TodoDTO getOne(Long tno) {
        // 실제로는, 디비에서 데이터를 받아 와야 하지만 더미 데이터 이용
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTno(5L);
        todoDTO.setTitle("하나 조회 더미 데이터");
        todoDTO.setDueDate(LocalDate.now());
        return todoDTO;
    }
}
