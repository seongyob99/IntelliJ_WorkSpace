package com.busanit501.helloworld.food.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
@Data
@Builder

    public class FoodVO {
        private Long tno;
        private String title;
        private LocalDate dueDate;
        private boolean finished;
    }
