package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@Log4j2
public class MemberServiceTest {
    private MemberService memberService;

    @BeforeEach
    public void ready() {memberService = MemberService.INSTANCE;
    }


    // 추가
    @Test
    public void testInsert() throws SQLException {
        MemberDTO memberDTO = MemberDTO.builder()
                .name("멤버 샘플")
                .dueDate(LocalDate.now())
                .build();
        memberService.register(memberDTO);
    }


    // 전체 조회
    @Test
    public void testSelectAll() throws SQLException {
        List<MemberDTO> dtoList = memberService.listAll();
        for (MemberDTO memberDTO:dtoList) {
            log.info(memberDTO);
        }
    }

    // 하나 조회
    @Test
    public void testSelectOne() throws SQLException {
        MemberDTO memberDTO = memberService.get(8L);
        log.info("하나 조회 memberDTO" + memberDTO);
    }

    // 수정
    @Test
    public void testUpdateOne() throws SQLException {
        MemberDTO memberDTO = MemberDTO.builder()
                .mno(14L)
                .name("이름 수정 테스트")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();
        memberService.update(memberDTO);

        log.info("하나 조회. + memberDTO" + memberDTO);
    }

    // 삭제
    @Test
    public void testDeleteOne() throws SQLException {
        memberService.delete(12L);
    }

}