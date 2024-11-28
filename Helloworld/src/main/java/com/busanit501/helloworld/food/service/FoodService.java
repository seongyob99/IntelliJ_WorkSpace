package com.busanit501.helloworld.food.service;

import com.busanit501.helloworld.food.dao.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.jdbcex.dao.TodoDAO;
import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.dto.TodoVO;
import com.busanit501.helloworld.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Log4j2
public enum FoodService {
    INSTANCE;

    private FoodDAO foodDAO;
    private ModelMapper modelMapper;

    FoodService() {
        foodDAO = new FoodDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //register
    public void register(FoodDTO foodDTO) throws SQLException {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        log.info("foodVo : " + foodVO);
        foodDAO.insert(foodVO);
    }

    //전체 조회
    public List<FoodDTO> listAll() throws SQLException {
        List<FoodVO> voList = foodDAO.selectAll();
        log.info("voList : " + voList);
        List<FoodDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, FoodDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    //하나 조회
    public FoodDTO get(Long fno) throws SQLException {
        log.info("fno : " + fno);
        ///  디비에서 하나 조회 결과 받았음.
        FoodVO foodVO = foodDAO.selectOne(fno);
        // VO -> DTO 변환 작업.
        FoodDTO foodDTO = modelMapper.map(foodVO,FoodDTO.class);
        return foodDTO;

    }

    //수정
    public void update(FoodDTO foodDTO) throws SQLException {
        log.info("FoodDTO : " + foodDTO);
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodDAO.updateOne(foodVO);
    }

    //삭제
    public void delete(Long fno) throws SQLException {
        foodDAO.deleteFood(fno);
    }

}