package com.busanit501.springpratice.service;

import com.busanit501.springpratice.dto.FoodDTO;

import javax.validation.Valid;
import java.util.List;

public interface FoodService {
    void register(FoodDTO foodDTO);
    List<FoodDTO> getAll();
    FoodDTO getOne(Long fno);
    void delete(Long fno);
    void update(FoodDTO foodDTO);
}
