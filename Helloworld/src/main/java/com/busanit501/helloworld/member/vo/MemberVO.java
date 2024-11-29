package com.busanit501.helloworld.member.vo;


import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MemberVO {
    private String name;
    private LocalDate dueDate;
    private String mid;
    private String mpw;
    private String uuid;
    private boolean finished;
}
