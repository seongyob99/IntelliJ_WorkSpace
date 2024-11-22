package com.busanit501.helloworld.food.service;

import com.busanit501.helloworld.food.dto.FoodDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum FoodService {
    INSTANCE;

    // 글 등록 기능
    public void register(FoodDTO foodDTO) {
        // DB에 데이터 쓰는 작업, insert
        System.out.println("글쓰기 작업하는 기능 콘솔출력");
    }

    // 조회, 샘플 (하드코딩, 더미)
    public List<FoodDTO> getList() {
        // DB에서 데이터 조회 후 전달하기
        List<FoodDTO> foodList = IntStream.range(0, 10)
                .mapToObj(i -> {
                    FoodDTO foodDTO = new FoodDTO();
                    foodDTO.setTitle("test" + i);
                    foodDTO.setTno((long) i);
                    foodDTO.setDueDate(LocalDate.now());
                    return foodDTO; // foodDTO 객체 반환
                })
                .collect(Collectors.toList());

        return foodList; // 리스트 반환
    }

    // 하나 조회, 상세보기, 게시글에서, 게시글 번호 클릭시 나타나는 데이터
    public FoodDTO getOne(Long tno) {
        // 실제로는, 디비에서 데이터를 받아 와야 하지만 더미 데이터 이용
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setTno(5L);
        foodDTO.setTitle("하나 조회 더미 데이터");
        foodDTO.setDueDate(LocalDate.now());
        return foodDTO; // foodDTO 객체 반환
    }
}
