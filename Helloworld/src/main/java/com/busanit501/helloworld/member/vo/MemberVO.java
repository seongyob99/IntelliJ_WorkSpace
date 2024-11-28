package com.busanit501.helloworld.member.vo;


import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private Long mno;
    private String name;
    private LocalDate dueDate;
    private boolean finished;
}
