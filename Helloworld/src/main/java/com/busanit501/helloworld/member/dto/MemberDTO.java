package com.busanit501.helloworld.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MemberDTO {
    private String name;
    private LocalDate dueDate;
    private String mid;
    private String mpw;
    private String uuid;
    private boolean finished;
}
