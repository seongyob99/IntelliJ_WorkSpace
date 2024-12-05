package com.busanit501.springpratice.service;

import com.busanit501.springpratice.domain.FoodVO;
import com.busanit501.springpratice.dto.FoodDTO;
import com.busanit501.springpratice.mapper.FoodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{

    private final FoodMapper foodMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(FoodDTO foodDTO) {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodMapper.insert(foodVO);

    }


    @Override
    public List<FoodDTO> getAll() {
        List<FoodDTO> list = foodMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo,FoodDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    //상세 조회 (한개 선택)
    @Override
    public FoodDTO getOne(Long fno) {
        FoodVO foodVO= foodMapper.selectOne(fno);
        FoodDTO foodDTO = modelMapper.map(foodVO,FoodDTO.class);
        return foodDTO;
    }

    //삭제
    @Override
    public void delete(Long fno) {
        foodMapper.delete(fno);
    }

    @Override
    public void update(FoodDTO foodDTO) {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodMapper.update(foodVO);
    }

}