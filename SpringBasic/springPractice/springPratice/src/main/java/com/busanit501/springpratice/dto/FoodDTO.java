package com.busanit501.springpratice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDTO {
    private Long fno;
    @NotEmpty
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    @NotEmpty
    private String writer;
}
