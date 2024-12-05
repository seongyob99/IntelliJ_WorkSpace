package com.busanit501.springpratice.domain;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodVO {
    private Long fno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;
}